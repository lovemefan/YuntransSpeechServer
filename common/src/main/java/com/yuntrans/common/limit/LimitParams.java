/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:29
 */
package com.yuntrans.common.limit;


import java.util.Objects;

public class LimitParams {
    private String userId;

    private String appkey;

    private String namespace;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LimitParams))
            return false;
        LimitParams other = (LimitParams)o;
        if (!other.canEqual(this))
            return false;
        Object this$userId = getUserId(), other$userId = other.getUserId();
        if (!Objects.equals(this$userId, other$userId))
            return false;
        Object this$appkey = getAppkey(), other$appkey = other.getAppkey();
        if (!Objects.equals(this$appkey, other$appkey))
            return false;
        Object this$namespace = getNamespace(), other$namespace = other.getNamespace();
        return Objects.equals(this$namespace, other$namespace);
    }

    protected boolean canEqual(Object other) {
        return other instanceof LimitParams;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $userId = getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        Object $appkey = getAppkey();
        result = result * 59 + (($appkey == null) ? 43 : $appkey.hashCode());
        Object $namespace = getNamespace();
        return result * 59 + (($namespace == null) ? 43 : $namespace.hashCode());
    }

    public String toString() {
        return "LimitParams(userId=" + getUserId() + ", appkey=" + getAppkey() + ", namespace=" + getNamespace() + ")";
    }

    public String getUserId() {
        return this.userId;
    }

    public String getAppkey() {
        return this.appkey;
    }

    public String getNamespace() {
        return this.namespace;
    }
}
