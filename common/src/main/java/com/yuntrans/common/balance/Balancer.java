/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:38
 */
package com.yuntrans.common.balance;

import com.yuntrans.common.exception.YuntransException;

public interface Balancer {
    String balance(String paramString) throws YuntransException;
}
