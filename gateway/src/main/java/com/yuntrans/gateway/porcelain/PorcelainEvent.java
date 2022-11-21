/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:25
 */
package com.yuntrans.gateway.porcelain;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuntrans.common.status.YuntransStatus;

import java.util.Objects;

public class PorcelainEvent {
    private String name;

    private YuntransStatus status;

    private ObjectNode payload;

    public PorcelainEvent(String name, YuntransStatus status, ObjectNode payload) {
        this.name = name;
        this.status = status;
        this.payload = payload;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(YuntransStatus status) {
        this.status = status;
    }

    public void setPayload(ObjectNode payload) {
        this.payload = payload;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PorcelainEvent))
            return false;
        PorcelainEvent other = (PorcelainEvent)o;
        if (!other.canEqual(this))
            return false;
        Object this$name = getName(), other$name = other.getName();
        if (!Objects.equals(this$name, other$name))
            return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if (!Objects.equals(this$status, other$status))
            return false;
        Object this$payload = getPayload(), other$payload = other.getPayload();
        return Objects.equals(this$payload, other$payload);
    }

    protected boolean canEqual(Object other) {
        return other instanceof PorcelainEvent;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $status = getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        Object $payload = getPayload();
        return result * 59 + (($payload == null) ? 43 : $payload.hashCode());
    }

    public String toString() {
        return "PorcelainEvent(name=" + getName() + ", status=" + getStatus() + ", payload=" + getPayload() + ")";
    }

    public PorcelainEvent() {}

    public String getName() {
        return this.name;
    }

    public YuntransStatus getStatus() {
        return this.status;
    }

    public ObjectNode getPayload() {
        return this.payload;
    }
}

