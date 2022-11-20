/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:39
 */
package com.yuntrans.common.exception;

import com.yuntrans.common.status.YuntransStatus;

public class RetryableException extends YuntransException{
    public RetryableException(YuntransStatus yuntransStatus) {
        super(yuntransStatus);
    }
}
