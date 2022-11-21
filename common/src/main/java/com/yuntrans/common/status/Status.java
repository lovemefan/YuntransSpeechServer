/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:50
 */
package com.yuntrans.common.status;

import com.yuntrans.common.exception.YuntransException;

public interface Status {
    final int code = 0;

    final String name = null;

    final String text = null;

    final String serviceName = null;
    Status modify(String text);
    YuntransException toException();
    int getCode();
    String getName();
    String getText();
    Throwable getCause();
    String getStatusText(String serviceName);
}
