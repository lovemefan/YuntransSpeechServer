/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:00
 */
package com.yuntrans.gateway.core;
import com.yuntrans.common.task.YuntransTaskContext;
import com.yuntrans.gateway.core.context.ConnectionContext;
import com.yuntrans.gateway.core.context.GatewayTaskContext;
import io.netty.handler.codec.http.HttpRequest;

public class ContextFacade {
    private static final String HTTP_HEADER_X_FORWARDED_FOR = "X-Forwarded-For";

    private static final String HTTP_HEADER_X_REAL_IP = "X-Real-IP";

    private static final String UNKNOWN_IP = "unknown";

    private static final String HTTP_HEADER_EAGLE_EYE_TRACE_ID = "EagleEye-TraceId";

    private ConnectionContext connCtx;

    private YuntransTaskContext yuntransCtx;

    private GatewayTaskContext gwCtx;

    private String mdcId;

    private final String region;

    private String clientIp;

    private String routeIp;

    private String authToken;

    private String traceId;

    private long taskStartTime;

    private long lastMessageTime;

    private int dataCount;

    private int dataSize;

    public ContextFacade() {
        this.region = "";
        this.clientIp = "unknown";
        this.routeIp = "unknown";
        this.authToken = "";
        this.taskStartTime = System.currentTimeMillis();
        this.lastMessageTime = System.currentTimeMillis();
        this.dataCount = 0;
        this.dataSize = 0;
    }

    public ContextFacade(ConnectionContext connCtx, YuntransTaskContext yuntransCtx, GatewayTaskContext gwCtx, String region) {
        this.connCtx = connCtx;
        this.yuntransCtx = yuntransCtx;
        this.gwCtx = gwCtx;
        this.region = region;
        this.clientIp = "unknown";
        this.routeIp = "unknown";
        this.authToken = "";
        this.taskStartTime = System.currentTimeMillis();
        this.lastMessageTime = System.currentTimeMillis();
        this.dataCount = 0;
        this.dataSize = 0;
    }

    public long getTaskDuration() {
        return System.currentTimeMillis() - this.taskStartTime;
    }

    public long getTaskLatency() {
        return System.currentTimeMillis() - this.lastMessageTime;
    }

    public ConnectionContext getConnCtx() {
        return this.connCtx;
    }

    public YuntransTaskContext getYuntransCtx() {
        return this.yuntransCtx;
    }

    public GatewayTaskContext getGwCtx() {
        return this.gwCtx;
    }

    public String getRegion() {
        return this.region;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getRouteIp() {
        return this.routeIp;
    }

    public void setRouteIp(String routeIp) {
        this.routeIp = routeIp;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMdcId() {
        return this.mdcId;
    }

    public void setMdcId(String mdcId) {
        this.mdcId = mdcId;
    }

    public long getTaskStartTime() {
        return this.taskStartTime;
    }

    public void setTaskStartTime(long taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public long getLastMessageTime() {
        return this.lastMessageTime;
    }

    public void setLastMessageTime(long lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public int getDataCount() {
        return this.dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public void resetTaskStartTime() {
        this.taskStartTime = System.currentTimeMillis();
    }

    public void resetLastMessageTime() {
        this.lastMessageTime = System.currentTimeMillis();
    }

    public void resetDataCount() {
        this.dataCount = 0;
    }

    public void resetDataSize() {
        this.dataSize = 0;
    }

    public void accumulateDataCount(int count) {
        this.dataCount += count;
    }

    public void accumulateDataSize(int size) {
        this.dataSize += size;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void updateContext(ConnectionContext connCtx, YuntransTaskContext yuntransCtx, GatewayTaskContext gwCtx) {
        this.connCtx = connCtx;
        this.yuntransCtx = yuntransCtx;
        this.gwCtx = gwCtx;
    }

    public void parseHttpRequest(HttpRequest httpRequest) {
        if (httpRequest.headers().contains("X-Forwarded-For")) {
            this.routeIp = httpRequest.headers().get("X-Forwarded-For");
            int index = this.routeIp.indexOf(',');
            if (index < 0) {
                this.clientIp = this.routeIp;
            } else {
                this.clientIp = this.routeIp.substring(0, index);
            }
        } else if (httpRequest.headers().contains("X-Real-IP")) {
            this.clientIp = httpRequest.headers().get("X-Real-IP");
            this.routeIp = this.clientIp;
        }
        httpRequest.headers().set("X-Real-Ip", this.clientIp);
        if (httpRequest.headers().contains("X-Yuntrans-Token"))
            this.authToken = httpRequest.headers().get("X-Yuntrans-Token");
        if (httpRequest.headers().contains("EagleEye-TraceId"))
            this.traceId = httpRequest.headers().get("EagleEye-TraceId");
    }
}

