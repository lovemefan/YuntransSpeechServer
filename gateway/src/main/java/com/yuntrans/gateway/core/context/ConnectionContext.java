/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:02
 */
package com.yuntrans.gateway.core.context;


public class ConnectionContext {
    private String connId;

    private String userId;

    private String traceId;

    public ConnectionContext(String connId, String userId, String traceId) {
        this.connId = connId;
        this.userId = userId;
        this.traceId = traceId;
    }

    public static ConnectionContextBuilder builder() {
        return new ConnectionContextBuilder();
    }

    public static class ConnectionContextBuilder {
        private String connId;

        private String userId;

        private String traceId;

        public ConnectionContextBuilder connId(String connId) {
            this.connId = connId;
            return this;
        }

        public ConnectionContextBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public ConnectionContextBuilder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public ConnectionContext build() {
            return new ConnectionContext(this.connId, this.userId, this.traceId);
        }

        public String toString() {
            return "ConnectionContext.ConnectionContextBuilder(connId=" + this.connId + ", userId=" + this.userId + ", traceId=" + this.traceId + ")";
        }
    }

    public void setConnId(String connId) {
        this.connId = connId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ConnectionContext))
            return false;
        ConnectionContext other = (ConnectionContext)o;
        if (!other.canEqual(this))
            return false;
        Object this$connId = getConnId(), other$connId = other.getConnId();
        if ((this$connId == null) ? (other$connId != null) : !this$connId.equals(other$connId))
            return false;
        Object this$userId = getUserId(), other$userId = other.getUserId();
        if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId))
            return false;
        Object this$traceId = getTraceId(), other$traceId = other.getTraceId();
        return !((this$traceId == null) ? (other$traceId != null) : !this$traceId.equals(other$traceId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConnectionContext;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $connId = getConnId();
        result = result * 59 + (($connId == null) ? 43 : $connId.hashCode());
        Object $userId = getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        Object $traceId = getTraceId();
        return result * 59 + (($traceId == null) ? 43 : $traceId.hashCode());
    }

    public String toString() {
        return "ConnectionContext(connId=" + getConnId() + ", userId=" + getUserId() + ", traceId=" + getTraceId() + ")";
    }

    public ConnectionContext() {}

    public String getConnId() {
        return this.connId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getTraceId() {
        return this.traceId;
    }
}
