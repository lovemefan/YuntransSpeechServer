/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:40
 */
package com.yuntrans.common.utils;

import java.util.ArrayList;
import java.util.List;

public class ParamsLoggerEntry {
    private static final int DEFAULT_FIELD_COUNT = 50;

    private final ParamsLogger logger;

    private final List<NameValuePair<Object>> params = new ArrayList<>(50);

    ParamsLoggerEntry(ParamsLogger logger) {
        this.logger = logger;
    }

    public ParamsLoggerEntry add(String name, Object value) {
        this.params.add(new NameValuePair(name, value));
        return this;
    }

    public void write() {
        this.logger.write(this.params);
    }
}
