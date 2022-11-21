/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:19
 */
package com.yuntrans.gateway.websocket;


import com.fasterxml.jackson.annotation.JsonProperty;

public class WsMessageHeader {
    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("task_id")
    private String taskId;

    private String namespace;

    private String name;

    private String appkey;

    private int status;

    @JsonProperty("status_text")
    private String statusText;

    @JsonProperty("service_group")
    private String serviceGroup;

    private String version;

    @JsonProperty("model_id")
    private String modelId;

    @JsonProperty("message_id")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @JsonProperty("task_id")
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

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonProperty("status_text")
    public void setStatusText(String statusText) {
        this.statusText = statusText;
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

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WsMessageHeader))
            return false;
        WsMessageHeader other = (WsMessageHeader)o;
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
        if (getStatus() != other.getStatus())
            return false;
        Object this$statusText = getStatusText(), other$statusText = other.getStatusText();
        if ((this$statusText == null) ? (other$statusText != null) : !this$statusText.equals(other$statusText))
            return false;
        Object this$serviceGroup = getServiceGroup(), other$serviceGroup = other.getServiceGroup();
        if ((this$serviceGroup == null) ? (other$serviceGroup != null) : !this$serviceGroup.equals(other$serviceGroup))
            return false;
        Object this$version = getVersion(), other$version = other.getVersion();
        if ((this$version == null) ? (other$version != null) : !this$version.equals(other$version))
            return false;
        Object this$modelId = getModelId(), other$modelId = other.getModelId();
        return !((this$modelId == null) ? (other$modelId != null) : !this$modelId.equals(other$modelId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WsMessageHeader;
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
        result = result * 59 + getStatus();
        Object $statusText = getStatusText();
        result = result * 59 + (($statusText == null) ? 43 : $statusText.hashCode());
        Object $serviceGroup = getServiceGroup();
        result = result * 59 + (($serviceGroup == null) ? 43 : $serviceGroup.hashCode());
        Object $version = getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        Object $modelId = getModelId();
        return result * 59 + (($modelId == null) ? 43 : $modelId.hashCode());
    }

    public String toString() {
        return "WsMessageHeader(messageId=" + getMessageId() + ", taskId=" + getTaskId() + ", namespace=" + getNamespace() + ", name=" + getName() + ", appkey=" + getAppkey() + ", status=" + getStatus() + ", statusText=" + getStatusText() + ", serviceGroup=" + getServiceGroup() + ", version=" + getVersion() + ", modelId=" + getModelId() + ")";
    }

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

    public int getStatus() {
        return this.status;
    }

    public String getStatusText() {
        return this.statusText;
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
}
