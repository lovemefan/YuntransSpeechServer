/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 16:33
 */
package com.yuntrans.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ParamsJsonLogger extends ParamsLoggerBase {
    private static final String PARAM_TIME = "time";

    private static final String PARAM_LOGGER = "logger";

    private static final String PARAM_APP = "app";

    private static final String PARAM_GROUP = "group";

    private static final String PARAM_IP = "ip";

    private final Logger logger;

    ParamsJsonLogger(String name, String host, String group, String ip) {
        super(name, host, group, ip);
        this.logger = LoggerFactory.getLogger(name);
    }

    public void write(List<NameValuePair<Object>> params) {
        ObjectNode json = Json.newTreeObject();
        json.put("time", TimeUtil.getReadableTime());
        json.put("logger", this.name);
        json.put("app", this.app);
        json.put("group", this.group);
        json.put("ip", this.ip);
        for (NameValuePair<Object> pair : params) {
            String name = pair.getName();
            Object value = pair.getValue();
            if (StringUtils.nullOrEmpty(name))
                continue;
            if (value == null) {
                json.put(name, "");
                continue;
            }
            if (value instanceof ObjectNode) {
                json.set(name, (JsonNode)value);
                continue;
            }
            json.put(name, value.toString());
        }
        this.logger.info(Json.toStringOrEmpty(json));
    }
}
