/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:32
 */
package com.yuntrans.common.logGather.domain;


import com.alibaba.fastjson.JSON;
import java.io.Serializable;

public class YuntransLog implements Serializable {
    private static final long serialVersionUID = -4618426558262463633L;

    private String time;

    private String app;

    private String ip;

    private String app_key;

    private String request_id;

    private String session_id;

    private String device_id;

    private String backend_app;

    private Object extend;

    private Integer status_code;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getApp_key() {
        return this.app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getSession_id() {
        return this.session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getDevice_id() {
        return this.device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getBackend_app() {
        return this.backend_app;
    }

    public void setBackend_app(String backend_app) {
        this.backend_app = backend_app;
    }

    public Object getExtend() {
        return this.extend;
    }

    public Integer getStatus_code() {
        return this.status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    public YuntransLog() {}

    public YuntransLog(String time, String app, String ip) {
        this.time = time;
        this.app = app;
        this.ip = ip;
    }

    public YuntransLog(String time, String app, String ip, String app_key, String request_id, String session_id, String device_id, String backend_app, Object extend, Integer status_code) {
        this.time = time;
        this.app = app;
        this.ip = ip;
        this.app_key = app_key;
        this.request_id = request_id;
        this.session_id = session_id;
        this.device_id = device_id;
        this.backend_app = backend_app;
        this.extend = extend;
        this.status_code = status_code;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
