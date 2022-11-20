/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:35
 */
package com.yuntrans.common.status;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuntrans.common.exception.RetryableException;
import com.yuntrans.common.exception.YuntransException;
import com.yuntrans.common.utils.Json;

public class YuntransStatus {
    public static final YuntransStatus SUCCESS = new YuntransStatus(20000000, "SUCCESS", "Success.");

    public static final YuntransStatus COMMON_ERROR = new YuntransStatus(30000000, "COMMON_ERROR", "Common error!");

    public static final YuntransStatus CLIENT_ERROR = new YuntransStatus(40000000, "CLIENT_ERROR", "Client error!");

    public static final YuntransStatus ACCESS_DENIED = new YuntransStatus(40000001, "ACCESS_DENIED", "Access denied!");

    public static final YuntransStatus MESSAGE_INVALID = new YuntransStatus(40000002, "MESSAGE_INVALID", "Message invalid!");

    public static final YuntransStatus PARAMETER_INVALID = new YuntransStatus(40000003, "PARAMETER_INVALID", "Parameter invalid!");

    public static final YuntransStatus IDLE_TIMEOUT = new YuntransStatus(40000004, "IDLE_TIMEOUT", "Idle timeout!");

    public static final YuntransStatus TOO_MANY_REQUESTS = new YuntransStatus(40000005, "TOO_MANY_REQUESTS", "Too many requests!");

    public static final YuntransStatus PAGE_NUMBER_INVALID = new YuntransStatus(40000006, "PAGE_NUMBER_INVALID", "Page number is invalid!");

    public static final YuntransStatus PAGE_SIZE_INVALID = new YuntransStatus(40000007, "PAGE_SIZE_INVALID", "Page size is invalid!");

    public static final YuntransStatus PAGE_OFFSET_TOO_LARGE = new YuntransStatus(40000008, "PAGE_OFFSET_TOO_LARGE", "Page size is invalid!");

    public static final YuntransStatus AUDIO_DECODE_ERROR = new YuntransStatus(40000009, "AUDIO_DECODE_ERROR", "Decode audio error!");

    public static final YuntransStatus FREE_TRIAL_EXPIRED = new YuntransStatus(40000010, "FREE_TRIAL_EXPIRED", "The free trial has expired!");

    public static final YuntransStatus SERVER_ERROR = new YuntransStatus(50000000, "SERVER_ERROR", "Server error!");

    public static final YuntransStatus GRPC_ERROR = new YuntransStatus(50000001, "GRPC_ERROR", "Grpc error!");

    public static final String DELIMITER = ":";

    private final int code;

    private final String name;

    private final String text;

    private final Throwable cause;

    public YuntransStatus(int code, String name, String text) {
        this.code = code;
        this.name = name;
        this.text = text;
        this.cause = null;
    }

    private YuntransStatus(int code, String name, String text, Throwable cause) {
        this.code = code;
        this.name = name;
        this.text = text;
        this.cause = cause;
    }

    public YuntransStatus modify(String text) {
        return new YuntransStatus(this.code, this.name, text, this.cause);
    }

    public YuntransStatus modify(String text, Throwable cause) {
        return new YuntransStatus(this.code, this.name, text, cause);
    }

    public YuntransStatus modify(Throwable cause) {
        return new YuntransStatus(this.code, this.name, this.text, cause);
    }

    public YuntransException toException() {
        return new YuntransException(this);
    }

    public RetryableException toRetryableException() {
        return new RetryableException(this);
    }

    public String getStatusText(String serviceName) {
        return serviceName + ":" + this.name + ":" + this.text;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String toString() {
        ObjectNode json = Json.newTreeObject();
        json.put("name", this.name);
        json.put("code", this.code);
        json.put("text", this.text);
        return Json.toStringOrEmpty(json);
    }
}
