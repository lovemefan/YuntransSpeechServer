/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:35
 */
package com.yuntrans.common.logGather.service;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.Appender;

import java.io.Serializable;

import com.yuntrans.common.logGather.domain.YuntransLog;
import com.yuntrans.common.logGather.util.HostUtil;
import com.yuntrans.common.logGather.util.TimeUtil;
import org.slf4j.LoggerFactory;

public class Gather implements Serializable {
    private static final long serialVersionUID = -5381195745887281938L;

    static String appName;

    static final Logger logger = ILoggerFactory.yuntransLogger;

    private static String getEnvAppName() {
        if (appName != null)
            return appName;
        String name = System.getenv("APP_NAME");
        if (name != null) {
            appName = name;
            return name;
        }
        name = System.getProperty("project.name");
        if (name != null) {
            appName = name;
            return name;
        }
        return "";
    }

    public static void log(YuntransLog yuntransLog) {
        try {
            if (logger == null) {
                LoggerFactory.getLogger("ROOT").error("YuntransLogger init error!");
                return;
            }
            if (logger.getAppender(ILoggerFactory.loggerAppender) == null || !ILoggerFactory.rollingFileAppender.isStarted() || logger.isAdditive())
                synchronized (logger) {
                    if (logger.getAppender(ILoggerFactory.loggerAppender) == null)
                        logger.addAppender((Appender)ILoggerFactory.rollingFileAppender);
                    if (!ILoggerFactory.rollingFileAppender.isStarted())
                        ILoggerFactory.rollingFileAppender.start();
                    if (logger.isAdditive())
                        logger.setAdditive(false);
                }
            logger.info(yuntransLog.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String appKey, String requestId, String sessionId, String deviceId, String backendApp, Object extend, Integer status_code) {
        log(new YuntransLog(TimeUtil.getCurrentTime(), getEnvAppName(), HostUtil.getHostName(), appKey, requestId, sessionId, deviceId, backendApp, extend, status_code));
    }

    public static void log(String appKey, String requestId, String sessionId, String deviceId, String backendApp, Object extend) {
        log(new YuntransLog(TimeUtil.getCurrentTime(), getEnvAppName(), HostUtil.getHostName(), appKey, requestId, sessionId, deviceId, backendApp, extend, Integer.valueOf(0)));
    }

    public static void log(String appKey, String requestId, String sessionId, String deviceId, Object extend) {
        log(new YuntransLog(TimeUtil.getCurrentTime(), getEnvAppName(), HostUtil.getHostName(), appKey, requestId, sessionId, deviceId, null, extend, Integer.valueOf(0)));
    }

    public static void log(String appKey, String requestId, String sessionId, Object extend) {
        log(new YuntransLog(TimeUtil.getCurrentTime(), getEnvAppName(), HostUtil.getHostName(), appKey, requestId, sessionId, null, null, extend, Integer.valueOf(0)));
    }

    public static void log(String appKey, String requestId, Object extend) {
        log(new YuntransLog(TimeUtil.getCurrentTime(), getEnvAppName(), HostUtil.getHostName(), appKey, requestId, null, null, null, extend, Integer.valueOf(0)));
    }


}
