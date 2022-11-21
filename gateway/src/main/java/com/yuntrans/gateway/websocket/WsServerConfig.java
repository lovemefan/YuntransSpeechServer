/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 18:14
 */
package com.yuntrans.gateway.websocket;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "yuntrans.cloud.gateway.websocket")
class WsServerConfig {
    private String address;

    private int port;

    private String path;

    private int workerNum;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WsServerConfig))
            return false;
        WsServerConfig other = (WsServerConfig)o;
        if (!other.canEqual(this))
            return false;
        Object this$address = getAddress(), other$address = other.getAddress();
        if ((this$address == null) ? (other$address != null) : !this$address.equals(other$address))
            return false;
        if (getPort() != other.getPort())
            return false;
        Object this$path = getPath(), other$path = other.getPath();
        return (Objects.equals(this$path, other$path)) && (getWorkerNum() == other.getWorkerNum());
    }

    protected boolean canEqual(Object other) {
        return other instanceof WsServerConfig;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $address = getAddress();
        result = result * 59 + (($address == null) ? 43 : $address.hashCode());
        result = result * 59 + getPort();
        Object $path = getPath();
        result = result * 59 + (($path == null) ? 43 : $path.hashCode());
        return result * 59 + getWorkerNum();
    }

    public String toString() {
        return "WsServerConfig(address=" + getAddress() + ", port=" + getPort() + ", path=" + getPath() + ", workerNum=" + getWorkerNum() + ")";
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    public String getPath() {
        return this.path;
    }

    public int getWorkerNum() {
        return this.workerNum;
    }
}
