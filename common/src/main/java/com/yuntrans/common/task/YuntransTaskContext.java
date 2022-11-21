/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 21:04
 */
package com.yuntrans.common.task;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
public class YuntransTaskContext {
    private String taskId;

    private String namespace;

    private String appkey;

    private String userId;

    private Executor executor;

    private String grayService;

    private ScheduledThreadPoolExecutor delayer;

    public YuntransTaskContext(String taskId, String namespace, String appkey, String userId, Executor executor, String grayService, ScheduledThreadPoolExecutor delayer) {
        this.taskId = taskId;
        this.namespace = namespace;
        this.appkey = appkey;
        this.userId = userId;
        this.executor = executor;
        this.grayService = grayService;
        this.delayer = delayer;
    }

    public static YuntransTaskContextBuilder builder() {
        return new YuntransTaskContextBuilder();
    }

    public static class YuntransTaskContextBuilder {
        private String taskId;

        private String namespace;

        private String appkey;

        private String userId;

        private Executor executor;

        private String grayService;

        private ScheduledThreadPoolExecutor delayer;

        public YuntransTaskContextBuilder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public YuntransTaskContextBuilder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public YuntransTaskContextBuilder appkey(String appkey) {
            this.appkey = appkey;
            return this;
        }

        public YuntransTaskContextBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public YuntransTaskContextBuilder executor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public YuntransTaskContextBuilder grayService(String grayService) {
            this.grayService = grayService;
            return this;
        }

        public YuntransTaskContextBuilder delayer(ScheduledThreadPoolExecutor delayer) {
            this.delayer = delayer;
            return this;
        }

        public YuntransTaskContext build() {
            return new YuntransTaskContext(this.taskId, this.namespace, this.appkey, this.userId, this.executor, this.grayService, this.delayer);
        }

        public String toString() {
            return "YuntransTaskContext.YuntransTaskContextBuilder(taskId=" + this.taskId + ", namespace=" + this.namespace + ", appkey=" + this.appkey + ", userId=" + this.userId + ", executor=" + this.executor + ", grayService=" + this.grayService + ", delayer=" + this.delayer + ")";
        }
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void setGrayService(String grayService) {
        this.grayService = grayService;
    }

    public void setDelayer(ScheduledThreadPoolExecutor delayer) {
        this.delayer = delayer;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof YuntransTaskContext))
            return false;
        YuntransTaskContext other = (YuntransTaskContext)o;
        if (!other.canEqual(this))
            return false;
        Object this$taskId = getTaskId(), other$taskId = other.getTaskId();
        if ((this$taskId == null) ? (other$taskId != null) : !this$taskId.equals(other$taskId))
            return false;
        Object this$namespace = getNamespace(), other$namespace = other.getNamespace();
        if ((this$namespace == null) ? (other$namespace != null) : !this$namespace.equals(other$namespace))
            return false;
        Object this$appkey = getAppkey(), other$appkey = other.getAppkey();
        if ((this$appkey == null) ? (other$appkey != null) : !this$appkey.equals(other$appkey))
            return false;
        Object this$userId = getUserId(), other$userId = other.getUserId();
        if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId))
            return false;
        Object this$executor = getExecutor(), other$executor = other.getExecutor();
        if ((this$executor == null) ? (other$executor != null) : !this$executor.equals(other$executor))
            return false;
        Object this$grayService = getGrayService(), other$grayService = other.getGrayService();
        if ((this$grayService == null) ? (other$grayService != null) : !this$grayService.equals(other$grayService))
            return false;
        Object this$delayer = getDelayer(), other$delayer = other.getDelayer();
        return !((this$delayer == null) ? (other$delayer != null) : !this$delayer.equals(other$delayer));
    }

    protected boolean canEqual(Object other) {
        return other instanceof YuntransTaskContext;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $taskId = getTaskId();
        result = result * 59 + (($taskId == null) ? 43 : $taskId.hashCode());
        Object $namespace = getNamespace();
        result = result * 59 + (($namespace == null) ? 43 : $namespace.hashCode());
        Object $appkey = getAppkey();
        result = result * 59 + (($appkey == null) ? 43 : $appkey.hashCode());
        Object $userId = getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        Object $executor = getExecutor();
        result = result * 59 + (($executor == null) ? 43 : $executor.hashCode());
        Object $grayService = getGrayService();
        result = result * 59 + (($grayService == null) ? 43 : $grayService.hashCode());
        Object $delayer = getDelayer();
        return result * 59 + (($delayer == null) ? 43 : $delayer.hashCode());
    }

    public String toString() {
        return "YuntransTaskContext(taskId=" + getTaskId() + ", namespace=" + getNamespace() + ", appkey=" + getAppkey() + ", userId=" + getUserId() + ", executor=" + getExecutor() + ", grayService=" + getGrayService() + ", delayer=" + getDelayer() + ")";
    }

    public YuntransTaskContext() {}

    public String getTaskId() {
        return this.taskId;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getAppkey() {
        return this.appkey;
    }

    public String getUserId() {
        return this.userId;
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public String getGrayService() {
        return this.grayService;
    }

    public ScheduledThreadPoolExecutor getDelayer() {
        return this.delayer;
    }
}

