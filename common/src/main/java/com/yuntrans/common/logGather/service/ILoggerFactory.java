/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:37
 */
package com.yuntrans.common.logGather.service;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.RollingPolicy;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;

public class ILoggerFactory {

    public static Logger yuntransLogger;

    public static RollingFileAppender rollingFileAppender = new RollingFileAppender();

    public static String loggerAppender = "yuntransAppender";

    static {
        try {
            LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
            String loggerName = "yuntransLogger";
            yuntransLogger = lc.getLogger(loggerName);
            rollingFileAppender.setName(loggerAppender);
            String logPath = "/home/logs/gather/yuntrans_gather.log";
            rollingFileAppender.setFile(logPath);
            rollingFileAppender.setRollingPolicy((RollingPolicy)createRollingPolicy(lc, (FileAppender)rollingFileAppender));
            rollingFileAppender.setContext((Context)lc);
            PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
            String pattern = "%m%n";
            patternLayoutEncoder.setPattern(pattern);
            patternLayoutEncoder.setContext((Context)lc);
            patternLayoutEncoder.start();
            rollingFileAppender.setEncoder((Encoder)patternLayoutEncoder);
            rollingFileAppender.start();
            yuntransLogger.setAdditive(false);
            if (yuntransLogger.getAppender(loggerAppender) == null)
                yuntransLogger.addAppender((Appender)rollingFileAppender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SizeAndTimeBasedRollingPolicy createRollingPolicy(LoggerContext loggerContext, FileAppender parent) {
        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
        policy.setContext((Context)loggerContext);
        int maxHistory = 5;
        policy.setMaxHistory(maxHistory);
        String maxFileSize = "512MB";
        setMaxFileSize(maxFileSize, policy);
        String totalSize = "2GB";
        setTotalSizeCap(totalSize, policy);
        policy.setCleanHistoryOnStart(false);
        String fileNamePattern = "/home//logs/gather/yuntrans_gather.%d{yyyy-MM-dd}.%i.log";
        policy.setFileNamePattern(fileNamePattern);
        policy.setParent(parent);
        policy.start();
        return policy;
    }

    public static void setMaxFileSize(String maxFileSize, SizeAndTimeBasedRollingPolicy policy) {
        try {
            policy.getClass().getMethod("setMaxFileSize", String.class).invoke(policy, maxFileSize);
        } catch (Exception e) {
            try {
                policy.getClass().getMethod("setMaxFileSize", FileSize.class).invoke(policy, FileSize.valueOf(maxFileSize));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void setTotalSizeCap(String setTotalSizeCap, SizeAndTimeBasedRollingPolicy policy) {
        try {
            policy.getClass().getMethod("setTotalSizeCap", String.class).invoke(policy, setTotalSizeCap);
        } catch (Exception e) {
            try {
                policy.getClass().getMethod("setTotalSizeCap", FileSize.class).invoke(policy, FileSize.valueOf(setTotalSizeCap));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
