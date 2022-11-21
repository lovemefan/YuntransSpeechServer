/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 16:30
 */
package com.yuntrans.common.utils;

public abstract class ParamsLoggerBase implements ParamsLogger {
    final String name;

    final String app;

    final String group;

    final String ip;

    ParamsLoggerBase(String name, String app, String group, String ip) {
        this.name = name;
        this.app = app;
        this.group = group;
        this.ip = ip;
    }

    public String getName() {
        return this.name;
    }

    public String getApp() {
        return this.app;
    }

    public String getGroup() {
        return this.group;
    }

    public String getIp() {
        return this.ip;
    }

    public ParamsLoggerEntry newEntry() {
        return new ParamsLoggerEntry(this);
    }
}
