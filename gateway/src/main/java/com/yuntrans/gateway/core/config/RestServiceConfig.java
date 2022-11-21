/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:50
 */
package com.yuntrans.gateway.core.config;


public class RestServiceConfig {
    private String path;

    private String service;

    public void setPath(String path) {
        this.path = path;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RestServiceConfig))
            return false;
        RestServiceConfig other = (RestServiceConfig)o;
        if (!other.canEqual(this))
            return false;
        Object this$path = getPath(), other$path = other.getPath();
        if ((this$path == null) ? (other$path != null) : !this$path.equals(other$path))
            return false;
        Object this$service = getService(), other$service = other.getService();
        return !((this$service == null) ? (other$service != null) : !this$service.equals(other$service));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestServiceConfig;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $path = getPath();
        result = result * 59 + (($path == null) ? 43 : $path.hashCode());
        Object $service = getService();
        return result * 59 + (($service == null) ? 43 : $service.hashCode());
    }

    public String toString() {
        return "RestServiceConfig(path=" + getPath() + ", service=" + getService() + ")";
    }

    public String getPath() {
        return this.path;
    }

    public String getService() {
        return this.service;
    }
}
