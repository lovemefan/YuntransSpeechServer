/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:45
 */
package com.yuntrans.common.utils;

public class JsonException extends Exception {
    JsonException(String message) {
        super(message);
    }

    JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}

