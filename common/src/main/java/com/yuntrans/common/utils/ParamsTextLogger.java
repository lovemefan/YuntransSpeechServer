/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 16:29
 */
package com.yuntrans.common.utils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ParamsTextLogger extends ParamsLoggerBase {
    private static final String DELIMITER = "|";

    private final Logger logger;

    ParamsTextLogger(String name, String app, String group, String ip) {
        super(name, app, group, ip);
        this.logger = LoggerFactory.getLogger(name);
    }

    public void write(List<NameValuePair<Object>> params) {
        StringBuilder builder = new StringBuilder();
        builder.append(TimeUtil.getReadableTime());
        builder.append("|").append(this.name);
        builder.append("|").append(this.app);
        builder.append("|").append(this.group);
        builder.append("|").append(this.ip);
        for (NameValuePair<Object> pair : params) {
            Object value = pair.getValue();
            if (value == null)
                value = "";
            builder.append("|").append(value.toString());
        }
        this.logger.info(builder.toString());
    }

}

