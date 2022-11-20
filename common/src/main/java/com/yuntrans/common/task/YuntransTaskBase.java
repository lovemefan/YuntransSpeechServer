/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 21:00
 */
package com.yuntrans.common.task;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.yuntrans.common.exception.YuntransException;
import com.yuntrans.common.status.YuntransStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public abstract class YuntransTaskBase<TResult> implements YuntransTask<TResult> {
    private static final Logger log = LoggerFactory.getLogger(YuntransTaskBase.class);

    protected final YuntransTaskContext context;

    protected final YuntransTaskListener<TResult> listener;

    private final CompletableFuture<TResult> future;

    protected boolean running = true;

    protected int taskStatus = 1;

    private final long createTime;

    public boolean isRunning() {
        return this.running;
    }

    public int getTaskStatus() {
        return this.taskStatus;
    }

    public boolean updateStatus(int newStatus) {
        switch (newStatus) {
            case 2:
                if (getTaskStatus() != 1)
                    return false;
                this.taskStatus = newStatus;
                return true;
            case 4:
                if (getTaskStatus() != 2) {
                    log.warn("Task status is {}, cannot stop!", Integer.valueOf(getTaskStatus()));
                    return false;
                }
                this.taskStatus = newStatus;
                return true;
            case 8:
                this.taskStatus = 8;
                return true;
        }
        triggerOnError((Throwable) YuntransStatus.CLIENT_ERROR.modify("Illegal task status").toException());
        return false;
    }

    protected YuntransTaskBase(YuntransTaskContext context, YuntransTaskListener<TResult> listener) {
        this.context = context;
        if (listener != null) {
            this.listener = listener;
            this.future = null;
        } else {
            this.listener = null;
            this.future = new CompletableFuture<>();
        }
        this.createTime = System.currentTimeMillis();
    }

    public TResult getResult() throws YuntransException {
        if (this.future == null)
            throw YuntransStatus.SERVER_ERROR.modify("Use listener instead of future!").toException();
        try {
            return this.future.get();
        } catch (InterruptedException e) {
            throw YuntransStatus.SERVER_ERROR.modify("Interrupted while waiting for task to finish!").toException();
        } catch (ExecutionException e) {
            return handleException(e);
        }
    }

    public TResult getResult(long waitTime, TimeUnit timeUnit) throws YuntransException {
        if (this.future == null)
            throw YuntransStatus.SERVER_ERROR.modify("Use listener instead of future!").toException();
        try {
            return this.future.get(waitTime, timeUnit);
        } catch (InterruptedException e) {
            throw YuntransStatus.SERVER_ERROR.modify("Interrupted while waiting for task to finish!").toException();
        } catch (ExecutionException|java.util.concurrent.TimeoutException e) {
            return handleException(e);
        }
    }

    private TResult handleException(Exception e) throws YuntransException {
        if (e.getCause() != null) {
            if (e.getCause() instanceof YuntransException)
                throw (YuntransException)e.getCause();
            throw YuntransStatus.SERVER_ERROR.modify(e.getCause()).toException();
        }
        throw YuntransStatus.SERVER_ERROR.toException();
    }

    public void cancel() {
        this.running = false;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public String getTaskId() {
        return this.context.getTaskId();
    }

    protected void scheduleHandler(YuntransEventHandler handler) {
        this.context.getExecutor().execute(() -> executeHandler(handler, false));
    }

    protected void scheduleHandler(YuntransEventHandler handler, boolean alwaysRun) {
        this.context.getExecutor().execute(() -> executeHandler(handler, alwaysRun));
    }

    protected void scheduleHandler(YuntransEventHandler handler, int timeout, TimeUnit timeUnit) {
        this.context.getDelayer().schedule(() -> executeHandler(handler, false), timeout, timeUnit);
    }

    protected void triggerOnCompleted(TResult result) {
        if (this.listener != null)
            this.listener.onCompleted(result);
        if (this.future != null)
            this.future.complete(result);
        this.running = false;
    }

    protected void triggerOnError(Throwable throwable) {
        if (this.listener != null)
            this.listener.onError(throwable);
        if (this.future != null)
            this.future.completeExceptionally(throwable);
        this.running = false;
    }

    private void executeHandler(YuntransEventHandler handler, boolean alwaysRun) {
        MDC.put("TASK_ID", this.context.getTaskId());
        try {
            if (this.running || alwaysRun) {
                handler.handleEvent();
            } else {
                log.debug("Ignore event, task not running");
            }
        } catch (Throwable throwable) {
            if (this.listener != null)
                this.listener.onError(throwable);
            if (this.future != null)
                this.future.completeExceptionally(throwable);
            if (this.running)
                cancel();
        } finally {
            MDC.clear();
        }
    }

    protected static boolean unbox(Boolean booleanValue) {
        return booleanValue != null && booleanValue;
    }

    protected static int unbox(Integer integerValue, int defaultValue) {
        return (integerValue == null) ? defaultValue : integerValue;
    }

    protected static <T> T defaultTo(T obj, T defaultValue) {
        return (obj == null) ? defaultValue : obj;
    }
}
