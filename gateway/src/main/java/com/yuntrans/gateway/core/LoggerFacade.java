/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:34
 */
package com.yuntrans.gateway.core;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuntrans.common.limit.LimitResult;
import com.yuntrans.common.logGather.domain.YuntransLog;
import com.yuntrans.common.logGather.service.Gather;
import com.yuntrans.common.status.Status;
import com.yuntrans.common.status.YuntransStatus;
import com.yuntrans.common.utils.*;
import com.yuntrans.gateway.core.ContextFacade;
import com.yuntrans.gateway.core.service.GatewayPrometheusService;
import com.yuntrans.gateway.core.service.GatewayTask;
import com.yuntrans.gateway.websocket.WsMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Component
@Slf4j
public class LoggerFacade {

    private static final String LOGGER_NAME_CONN_OPEN = "conn-open";

    private static final String LOGGER_NAME_TASK = "task";

    private static final String LOGGER_NAME_MESSAGE = "message";

    private static final String LOGGER_NAME_CONTEXT = "context";

    private static final String LOGGER_NAME_ROUTE = "route";

    private static final String LOGGER_NAME_ACCESS = "access";

    private static final String LOGGER_NAME_SLA = "sla";

    private static final String LOGGER_NAME_MEMORY = "netty-memory";

    private static final String LOGGER_NAME_LIMIT = "limit";

    private final ParamsLogger connOpenLogger;

    private final ParamsLogger taskLogger;

    private final ParamsLogger messageLogger;

    private final ParamsLogger contextLogger;

    private final ParamsLogger routeLogger;

    private final ParamsLogger accessLogger;

    private final ParamsLogger slaLogger;

    private final ParamsLogger memoryLogger;

    private final ParamsLogger limitLogger;

    private final GatewayPrometheusService prometheusService;

    private String appName;

    @Autowired
    public LoggerFacade(ParamsLoggerFactory paramsLoggerFactory, GatewayPrometheusService gatewayPrometheusService) {
        this.connOpenLogger = paramsLoggerFactory.getJsonLogger("conn-open");
        this.taskLogger = paramsLoggerFactory.getJsonLogger("task");
        this.messageLogger = paramsLoggerFactory.getJsonLogger("message");
        this.contextLogger = paramsLoggerFactory.getJsonLogger("context");
        this.routeLogger = paramsLoggerFactory.getJsonLogger("route");
        this.accessLogger = paramsLoggerFactory.getJsonLogger("access");
        this.slaLogger = paramsLoggerFactory.getJsonLogger("sla");
        this.memoryLogger = paramsLoggerFactory.getJsonLogger("netty-memory");
        this.limitLogger = paramsLoggerFactory.getJsonLogger("limit");
        this.prometheusService = gatewayPrometheusService;
    }

    public void logTask(GatewayTask<?> task, ContextFacade contextFacade, int status, String statusText) {
        logTask(null, task, contextFacade, status, statusText);
    }

    public void logTask(WsMessage startMessage, GatewayTask<?> task, ContextFacade contextFacade, Status status) {
        String statusText = status.getStatusText("Gateway");
        logTask(startMessage, task, contextFacade, status.getCode(), statusText);
    }

    public void logTask(GatewayTask<?> task, ContextFacade contextFacade, Status status) {
        String statusText = status.getStatusText("Gateway");
        logTask(null, task, contextFacade, status.getCode(), statusText);
    }

    public void logTask(WsMessage startMessage, GatewayTask<?> task, ContextFacade contextFacade, int status, String statusText) {
        long duration = contextFacade.getTaskDuration();
        long latency = contextFacade.getTaskLatency();
        if (contextFacade.getYuntransCtx() == null)
            return;
        String namespace = contextFacade.getYuntransCtx().getNamespace();
        String appkey = contextFacade.getYuntransCtx().getAppkey();
        String userId = contextFacade.getYuntransCtx().getUserId();
        this.taskLogger.newEntry()
                .add("task_id", contextFacade.getYuntransCtx().getTaskId())
                .add("conn_id", contextFacade.getConnCtx().getConnId())
                .add("appkey", appkey)
                .add("namespace", namespace)
                .add("duration", duration)
                .add("latency", latency)
                .add("status", status)
                .add("data_count", contextFacade.getDataCount())
                .add("data_size", contextFacade.getDataSize())

                .add("status_text", statusText)
                .add("gray_test", contextFacade.getYuntransCtx().getGrayService())
                .add("user_id", userId)
                .add("source_from", (contextFacade.getGwCtx() == null) ? "" :
                        GatewayContextUtil.getSourceFrom(contextFacade.getGwCtx().getClientContext()))
                .write();
        logAccess(task, contextFacade, status, statusText);
        logSla(contextFacade, status);
        Map<String, Object> dataMap = new LinkedHashMap<>(10);
        dataMap.put("conn_id", contextFacade.getConnCtx().getConnId());
        dataMap.put("namespace", contextFacade.getYuntransCtx().getNamespace());
        dataMap.put("start_timestamp", contextFacade.getTaskStartTime());
        dataMap.put("duration", duration);
        dataMap.put("latency", latency);
        dataMap.put("data_count", contextFacade.getDataCount());
        dataMap.put("data_size", contextFacade.getDataSize());
        dataMap.put("status_text", statusText);
        dataMap.put("backend_apps", (task == null) ? "" : String.join(",", (CharSequence[])task.getPosteriorApps()));
        dataMap.put("request", Json.toStringOrEmpty(startMessage));
        dataMap.put("gray_test", contextFacade.getYuntransCtx().getGrayService());
        YuntransLog yuntransLog = newYuntransLog(contextFacade, dataMap);
        if (yuntransLog != null) {
            yuntransLog.setStatus_code(status);
            Gather.log(yuntransLog);
        }
        this.prometheusService.incTaskCounter(namespace, appkey, userId, status);
        this.prometheusService.recordLatency(namespace, appkey, userId, latency);
    }

    public void logWsMessage(ContextFacade contextFacade, WsMessage message) {
        Map<String, Object> dataMap = new LinkedHashMap<>(10);
        YuntransLog yuntransLog = new YuntransLog();
        yuntransLog.setExtend(dataMap);
        ParamsLoggerEntry entry = this.messageLogger.newEntry();
        String appkey = (contextFacade.getYuntransCtx() == null) ? "" : contextFacade.getYuntransCtx().getAppkey();
        if (message.getHeader() != null) {
            entry
                    .add("task_id", message.getHeader().getTaskId())
                    .add("message_id", message.getHeader().getMessageId())
                    .add("namespace", message.getHeader().getNamespace())
                    .add("name", message.getHeader().getName())
                    .add("appkey", appkey)
                    .add("status", Integer.valueOf(message.getHeader().getStatus()))
                    .add("status_text", message.getHeader().getStatusText());
            yuntransLog.setStatus_code(Integer.valueOf(message.getHeader().getStatus()));
            yuntransLog.setRequest_id(message.getHeader().getTaskId());
            yuntransLog.setDevice_id(tryGetDeviceUuid(contextFacade));
            yuntransLog.setApp(getEnvAppName());
            yuntransLog.setTime(TimeUtil.getReadableTime());
            yuntransLog.setIp(NetworkUtil.getLocalAddress());
            yuntransLog.setApp_key(appkey);
            dataMap.put("name", message.getHeader().getName());
            dataMap.put("namespace", message.getHeader().getNamespace());
            dataMap.put("status_text", message.getHeader().getStatusText());
            dataMap.put("message_id", message.getHeader().getMessageId());
        }
        if (message.getPayload() != null) {
            entry.add("payload", message.getPayload());
            try {
                dataMap.put("payload", Json.toString(message.getPayload()));
            } catch (JsonException e) {
                log.error("Parse json failed ", (Throwable)e);
            }
        }
        entry.write();
        Gather.log(yuntransLog);
    }

    public void logBinary(ContextFacade contextFacade, int length) {
        if (contextFacade == null || contextFacade.getYuntransCtx() == null)
            return;
        this.messageLogger.newEntry()
                .add("task_id", contextFacade.getYuntransCtx().getTaskId())
                .add("message_id", "00000000000000000000000000000000")
                .add("namespace", contextFacade.getYuntransCtx().getNamespace())
                .add("name", "Binary")
                .add("appkey", contextFacade.getYuntransCtx().getAppkey())
                .add("status", Integer.valueOf(0))
                .add("status_text", "")
                .add("payload", Integer.valueOf(length))
                .write();
    }

    public void logClientContext(ContextFacade contextFacade, ObjectNode clientContext) {
        if (clientContext == null)
            return;
        this.contextLogger.newEntry()
                .add("task_id", contextFacade.getYuntransCtx().getTaskId())
                .add("context", clientContext)
                .write();
        Gather.log(newYuntransLog(contextFacade, Json.toStringOrEmpty(clientContext)));
    }

    public void logRoute(ContextFacade contextFacade) {
        Map<String, Object> dataMap = new LinkedHashMap<>(10);
        ParamsLoggerEntry entry = this.routeLogger.newEntry().add("task_id", contextFacade.getYuntransCtx().getTaskId()).add("groups", Json.toTreeObjectOrNull(contextFacade.getGwCtx().getGroups()));
        if (contextFacade.getGwCtx().getRoutedEndpoints() != null) {
            Map<String, String> mapped = (Map<String, String>)contextFacade.getGwCtx().getRoutedEndpoints().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, pair -> ((Endpoint)pair.getValue()).getName()));
            entry.add("routed", Json.toTreeObjectOrNull(mapped));
            dataMap.put("routed", mapped);
        }
        if (contextFacade.getGwCtx().getBalancedEndpoints() != null) {
            Map<String, String> mapped = (Map<String, String>)contextFacade.getGwCtx().getBalancedEndpoints().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, pair -> ((Endpoint)pair.getValue()).getName()));
            entry.add("balanced", Json.toTreeObjectOrNull(mapped));
            dataMap.put("balanced", mapped);
        }
        if (contextFacade.getYuntransCtx() != null) {
            entry.add("appkey", contextFacade.getYuntransCtx().getAppkey());
            entry.add("user_id", contextFacade.getYuntransCtx().getUserId());
            entry.add("namespace", contextFacade.getYuntransCtx().getNamespace());
        } else {
            entry.add("appkey", "");
            entry.add("user_id", "");
            entry.add("namespace", "");
        }
        entry.write();
        Gather.log(newYuntransLog(contextFacade, dataMap));
    }

    private void logAccess(GatewayTask<?> task, ContextFacade contextFacade, int status, String statusText) {
        this.accessLogger.newEntry()
                .add("app_key", contextFacade.getYuntransCtx().getAppkey())
                .add("request_id", contextFacade.getYuntransCtx().getTaskId())
                .add("session_id", contextFacade.getYuntransCtx().getTaskId())
                .add("device_uuid", tryGetDeviceUuid(contextFacade))
                .add("uid", contextFacade.getConnCtx().getUserId())
                .add("start_timestamp", Long.valueOf(contextFacade.getTaskStartTime()))
                .add("latency", Long.valueOf(contextFacade.getTaskLatency()))
                .add("status_code", Integer.valueOf(status))
                .add("status_message", statusText)
                .add("backend_apps", (task == null) ? "" : String.join(",", (CharSequence[])task.getPosteriorApps()))
                .write();
    }

    private void logSla(ContextFacade contextFacade, int status) {
        String code = "success";
        if (status >= YuntransStatus.SERVER_ERROR.getCode()) {
            code = "fail";
        } else if (status >= YuntransStatus.CLIENT_ERROR.getCode()) {
            return;
        }
        this.slaLogger.newEntry()
                .add("timestamp", Long.valueOf(System.currentTimeMillis()))
                .add("uid", contextFacade.getYuntransCtx().getUserId())
                .add("instance_id", contextFacade.getYuntransCtx().getNamespace())
                .add("product_type", contextFacade.getYuntransCtx().getNamespace())
                .add("product_code", "yuntrans")
                .add("count", "1")
                .add("code", code)
                .add("sli_id", "sli_yuntrans_error_" + contextFacade.getYuntransCtx().getNamespace())
                .add("sli_level", "loss_performance")
                .add("region", contextFacade.getRegion())
                .write();
    }

    public void logConnOpen(ContextFacade contextFacade, Status status) {
        int statusCode = status.getCode();
        String statusText = status.getStatusText("Gateway");
        logConnOpen(contextFacade, statusCode, statusText);
    }

    public void logConnOpen(ContextFacade contextFacade, int statusCode, String statusText) {
        long duration = contextFacade.getTaskDuration();
        this.connOpenLogger.newEntry()
                .add("conn_id", contextFacade.getConnCtx().getConnId())
                .add("client_ip", contextFacade.getClientIp())
                .add("route_ip", contextFacade.getRouteIp())
                .add("token", contextFacade.getAuthToken())
                .add("user_id", contextFacade.getConnCtx().getUserId())
                .add("latency", Long.valueOf(duration))
                .add("status", Integer.valueOf(statusCode))
                .add("status_text", statusText)
                .add("eagleeye_trace_id", contextFacade.getTraceId())
                .write();
    }

    private String tryGetDeviceUuid(ContextFacade contextFacade) {
        if (contextFacade.getGwCtx() == null)
            return "";
        if (contextFacade.getGwCtx().getClientContext() == null)
            return "";
        JsonNode node = contextFacade.getGwCtx().getClientContext().get("device");
        if (node == null)
            return "";
        if (!(node instanceof ObjectNode))
            return "";
        node = node.get("uuid");
        if (!(node instanceof com.fasterxml.jackson.databind.node.TextNode))
            return "";
        return node.asText();
    }

    private YuntransLog newYuntransLog(ContextFacade contextFacade, Object extend) {
        if (contextFacade.getYuntransCtx() == null || StringUtils.isEmpty(contextFacade.getYuntransCtx().getTaskId()))
            return null;
        YuntransLog yuntransLog = new YuntransLog();
        yuntransLog.setApp_key(contextFacade.getYuntransCtx().getAppkey());
        yuntransLog.setRequest_id(contextFacade.getYuntransCtx().getTaskId());
        yuntransLog.setDevice_id(tryGetDeviceUuid(contextFacade));
        yuntransLog.setApp(getEnvAppName());
        yuntransLog.setTime(TimeUtil.getReadableTime());
        yuntransLog.setIp(NetworkUtil.getLocalAddress());
        yuntransLog.setExtend(extend);
        return yuntransLog;
    }

    private String getEnvAppName() {
        if (this.appName != null)
            return this.appName;
        String name = System.getenv("APP_NAME");
        if (name != null) {
            this.appName = name;
            return name;
        }
        name = System.getProperty("project.name");
        if (name != null) {
            this.appName = name;
            return name;
        }
        return "";
    }

    public void logDirectMemory(AtomicLong memoryStat) {
        this.memoryLogger.newEntry()
                .add("direct_memory", Long.valueOf(memoryStat.get()))
                .write();
    }

    public void logLimit(LimitResult result, String appkey, String userId, String namespace) {
        this.limitLogger.newEntry()
                .add("appkey", appkey)
                .add("user_id", userId)
                .add("used", result.getUsed())
                .add("idle", result.getIdle())
                .add("total", result.getIdle() + result.getUsed())
                .add("namespace", namespace)
                .write();
    }
}
