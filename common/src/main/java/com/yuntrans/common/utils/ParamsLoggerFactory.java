/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 16:28
 */
package com.yuntrans.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParamsLoggerFactory {
    private final String app;

    private final String group;

    private final String ip = NetworkUtil.getLocalAddress();

    public ParamsLoggerFactory(String app, String group) {
        this.app = app;
        this.group = group;
    }

    public ParamsLogger getTextLogger(String name) {
        return new ParamsTextLogger(name, this.app, this.group, this.ip);
    }

    public ParamsLogger getJsonLogger(String name) {
        return new ParamsJsonLogger(name, this.app, this.group, this.ip);
    }
}

