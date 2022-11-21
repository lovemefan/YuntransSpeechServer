/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:18
 */
package com.yuntrans.gateway.websocket;


import com.fasterxml.jackson.databind.node.ObjectNode;

public class WsMessage {
    private WsMessageHeader header;

    private ObjectNode payload;

    private ObjectNode context;

    public void setHeader(WsMessageHeader header) {
        this.header = header;
    }

    public void setPayload(ObjectNode payload) {
        this.payload = payload;
    }

    public void setContext(ObjectNode context) {
        this.context = context;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WsMessage))
            return false;
        WsMessage other = (WsMessage)o;
        if (!other.canEqual(this))
            return false;
        Object this$header = getHeader(), other$header = other.getHeader();
        if ((this$header == null) ? (other$header != null) : !this$header.equals(other$header))
            return false;
        Object this$payload = getPayload(), other$payload = other.getPayload();
        if ((this$payload == null) ? (other$payload != null) : !this$payload.equals(other$payload))
            return false;
        Object this$context = getContext(), other$context = other.getContext();
        return !((this$context == null) ? (other$context != null) : !this$context.equals(other$context));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WsMessage;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $header = getHeader();
        result = result * 59 + (($header == null) ? 43 : $header.hashCode());
        Object $payload = getPayload();
        result = result * 59 + (($payload == null) ? 43 : $payload.hashCode());
        Object $context = getContext();
        return result * 59 + (($context == null) ? 43 : $context.hashCode());
    }

    public String toString() {
        return "WsMessage(header=" + getHeader() + ", payload=" + getPayload() + ", context=" + getContext() + ")";
    }

    public WsMessageHeader getHeader() {
        return this.header;
    }

    public ObjectNode getPayload() {
        return this.payload;
    }

    public ObjectNode getContext() {
        return this.context;
    }
}
