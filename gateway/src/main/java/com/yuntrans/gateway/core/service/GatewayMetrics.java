/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:43
 */
package com.yuntrans.gateway.core.service;

import com.yuntrans.common.utils.ParamsLogger;
import com.yuntrans.common.utils.ParamsLoggerFactory;
import com.yuntrans.gateway.core.LoggerFacade;
import io.netty.util.internal.PlatformDependent;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
@Service
@Slf4j
public class GatewayMetrics {

    private static final String LOGGER_NAME_METRICS = "metrics";

    private final LoggerFacade loggerFacade;

    private final ParamsLogger metricsLogger;

    private final AtomicInteger currentConnections = new AtomicInteger(0);

    private final AtomicInteger currentTasks = new AtomicInteger(0);

    private final AtomicInteger totalConnections = new AtomicInteger(0);

    private final AtomicInteger totalTasks = new AtomicInteger(0);

    private AtomicLong directMemory;

    @Autowired
    public GatewayMetrics(ParamsLoggerFactory paramsLoggerFactory, LoggerFacade loggerFacade) {
        this.metricsLogger = paramsLoggerFactory.getJsonLogger("metrics");
        this.loggerFacade = loggerFacade;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing bean '{}'", getClass().getSimpleName());
        Field field = ReflectionUtils.findField(PlatformDependent.class, "DIRECT_MEMORY_COUNTER");
        if (field != null) {
            field.setAccessible(true);
            try {
                this.directMemory = (AtomicLong)field.get(PlatformDependent.class);
            } catch (Exception e) {
                log.warn("Failed to get direct memory monitor!");
            }
        }
        log.info("Initialized bean '{}'", getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying bean '{}'", getClass().getSimpleName());
        log.info("Destroyed bean '{}'", getClass().getSimpleName());
    }

    @Scheduled(fixedRate = 1000L, initialDelay = 5000L)
    public void writeLog() {
        this.metricsLogger.newEntry()
                .add("cur_conn", this.currentConnections.get())
                .add("cur_task", this.currentTasks.get())
                .add("all_conn", this.totalConnections.get())
                .add("all_task", this.totalTasks.get())
                .write();
    }

    @Scheduled(fixedRate = 60000L, initialDelay = 1000L)
    private void monitorDirectMemory() {
        Optional.<AtomicLong>ofNullable(this.directMemory).ifPresent(this.loggerFacade::logDirectMemory);
    }

    public void increaseConnection() {
        this.currentConnections.incrementAndGet();
        this.totalConnections.incrementAndGet();
    }

    public void decreaseConnection() {
        this.currentConnections.decrementAndGet();
    }

    public void increaseTask() {
        this.currentTasks.incrementAndGet();
        this.totalTasks.incrementAndGet();
    }

    public void decreaseTask() {
        this.currentTasks.decrementAndGet();
    }
}
