/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:55
 */
package com.yuntrans.common.task;

import com.yuntrans.common.exception.YuntransException;
@FunctionalInterface
public interface YuntransEventHandler {
    void handleEvent() throws YuntransException;
}
