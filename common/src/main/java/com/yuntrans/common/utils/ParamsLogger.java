/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:38
 */
package com.yuntrans.common.utils;

import java.util.List;

public interface ParamsLogger {
    String getName();

    String getApp();

    String getGroup();

    String getIp();

    void write(List<NameValuePair<Object>> paramList);

    ParamsLoggerEntry newEntry();
}

