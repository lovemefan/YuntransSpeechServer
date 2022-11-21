/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:25
 */
package com.yuntrans.gateway.porcelain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PorcelainDirective {
    private String messageId;

    private String taskId;

    private String namespace;

    private String name;

    private String appkey;

    @JsonProperty("service_group")
    private String serviceGroup;

    private String version;

    @JsonProperty("model_id")
    private String modelId;

    private ObjectNode payload;

    public PorcelainDirective(String messageId, String taskId, String namespace, String name, String appkey, String serviceGroup, String version, String modelId, ObjectNode payload) {
        this.messageId = messageId;
        this.taskId = taskId;
        this.namespace = namespace;
        this.name = name;
        this.appkey = appkey;
        this.serviceGroup = serviceGroup;
        this.version = version;
        this.modelId = modelId;
        this.payload = payload;
    }

    public static PorcelainDirectiveBuilder builder() {
        return new PorcelainDirectiveBuilder();
    }

    public static class PorcelainDirectiveBuilder {
        private String messageId;

        private String taskId;

        private String namespace;

        private String name;

        private String appkey;

        private String serviceGroup;

        private String version;

        private String modelId;

        private ObjectNode payload;

        public PorcelainDirectiveBuilder messageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public PorcelainDirectiveBuilder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public PorcelainDirectiveBuilder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public PorcelainDirectiveBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PorcelainDirectiveBuilder appkey(String appkey) {
            this.appkey = appkey;
            return this;
        }

        @JsonProperty("service_group")
        public PorcelainDirectiveBuilder serviceGroup(String serviceGroup) {
            this.serviceGroup = serviceGroup;
            return this;
        }

        public PorcelainDirectiveBuilder version(String version) {
            this.version = version;
            return this;
        }

        @JsonProperty("model_id")
        public PorcelainDirectiveBuilder modelId(String modelId) {
            this.modelId = modelId;
            return this;
        }

        public PorcelainDirectiveBuilder payload(ObjectNode payload) {
            this.payload = payload;
            return this;
        }

        public PorcelainDirective build() {
            return new PorcelainDirective(this.messageId, this.taskId, this.namespace, this.name, this.appkey, this.serviceGroup, this.version, this.modelId, this.payload);
        }

        public String toString() {
            return "PorcelainDirective.PorcelainDirectiveBuilder(messageId=" + this.messageId + ", taskId=" + this.taskId + ", namespace=" + this.namespace + ", name=" + this.name + ", appkey=" + this.appkey + ", serviceGroup=" + this.serviceGroup + ", version=" + this.version + ", modelId=" + this.modelId + ", payload=" + this.payload + ")";
        }
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    @JsonProperty("service_group")
    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("model_id")
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public void setPayload(ObjectNode payload) {
        this.payload = payload;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PorcelainDirective))
            return false;
        PorcelainDirective other = (PorcelainDirective)o;
        if (!other.canEqual(this))
            return false;
        Object this$messageId = getMessageId(), other$messageId = other.getMessageId();
        if ((this$messageId == null) ? (other$messageId != null) : !this$messageId.equals(other$messageId))
            return false;
        Object this$taskId = getTaskId(), other$taskId = other.getTaskId();
        if ((this$taskId == null) ? (other$taskId != null) : !this$taskId.equals(other$taskId))
            return false;
        Object this$namespace = getNamespace(), other$namespace = other.getNamespace();
        if ((this$namespace == null) ? (other$namespace != null) : !this$namespace.equals(other$namespace))
            return false;
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
            return false;
        Object this$appkey = getAppkey(), other$appkey = other.getAppkey();
        if ((this$appkey == null) ? (other$appkey != null) : !this$appkey.equals(other$appkey))
            return false;
        Object this$serviceGroup = getServiceGroup(), other$serviceGroup = other.getServiceGroup();
        if ((this$serviceGroup == null) ? (other$serviceGroup != null) : !this$serviceGroup.equals(other$serviceGroup))
            return false;
        Object this$version = getVersion(), other$version = other.getVersion();
        if ((this$version == null) ? (other$version != null) : !this$version.equals(other$version))
            return false;
        Object this$modelId = getModelId(), other$modelId = other.getModelId();
        if ((this$modelId == null) ? (other$modelId != null) : !this$modelId.equals(other$modelId))
            return false;
        Object this$payload = getPayload(), other$payload = other.getPayload();
        return !((this$payload == null) ? (other$payload != null) : !this$payload.equals(other$payload));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PorcelainDirective;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $messageId = getMessageId();
        result = result * 59 + (($messageId == null) ? 43 : $messageId.hashCode());
        Object $taskId = getTaskId();
        result = result * 59 + (($taskId == null) ? 43 : $taskId.hashCode());
        Object $namespace = getNamespace();
        result = result * 59 + (($namespace == null) ? 43 : $namespace.hashCode());
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $appkey = getAppkey();
        result = result * 59 + (($appkey == null) ? 43 : $appkey.hashCode());
        Object $serviceGroup = getServiceGroup();
        result = result * 59 + (($serviceGroup == null) ? 43 : $serviceGroup.hashCode());
        Object $version = getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        Object $modelId = getModelId();
        result = result * 59 + (($modelId == null) ? 43 : $modelId.hashCode());
        Object $payload = getPayload();
        return result * 59 + (($payload == null) ? 43 : $payload.hashCode());
    }

    public String toString() {
        return "PorcelainDirective(messageId=" + getMessageId() + ", taskId=" + getTaskId() + ", namespace=" + getNamespace() + ", name=" + getName() + ", appkey=" + getAppkey() + ", serviceGroup=" + getServiceGroup() + ", version=" + getVersion() + ", modelId=" + getModelId() + ", payload=" + getPayload() + ")";
    }

    public PorcelainDirective() {}

    public String getMessageId() {
        return this.messageId;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getName() {
        return this.name;
    }

    public String getAppkey() {
        return this.appkey;
    }

    public String getServiceGroup() {
        return this.serviceGroup;
    }

    public String getVersion() {
        return this.version;
    }

    public String getModelId() {
        return this.modelId;
    }

    public ObjectNode getPayload() {
        return this.payload;
    }

    public String fullName() {
        return this.namespace + "." + this.name;
    }
}
