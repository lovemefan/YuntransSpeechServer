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

public enum YuntransStatus implements Status {
    SUCCESS(20000000, "SUCCESS", "Success."),

    COMMON_ERROR(30000000, "COMMON_ERROR", "Common error!"),

    CLIENT_ERROR(40000000, "CLIENT_ERROR", "Client error!"),

    ACCESS_DENIED(40000001, "ACCESS_DENIED", "Access denied!"),

    MESSAGE_INVALID(40000002, "MESSAGE_INVALID", "Message invalid!"),

    PARAMETER_INVALID(40000003, "PARAMETER_INVALID", "Parameter invalid!"),

    IDLE_TIMEOUT(40000004, "IDLE_TIMEOUT", "Idle timeout!"),

    TOO_MANY_REQUESTS(40000005, "TOO_MANY_REQUESTS", "Too many requests!"),

    PAGE_NUMBER_INVALID(40000006, "PAGE_NUMBER_INVALID", "Page number is invalid!"),

    PAGE_SIZE_INVALID(40000007, "PAGE_SIZE_INVALID", "Page size is invalid!"),

    PAGE_OFFSET_TOO_LARGE(40000008, "PAGE_OFFSET_TOO_LARGE", "Page size is invalid!"),

    AUDIO_DECODE_ERROR(40000009, "AUDIO_DECODE_ERROR", "Decode audio error!"),

    FREE_TRIAL_EXPIRED(40000010, "FREE_TRIAL_EXPIRED", "The free trial has expired!"),

    SERVER_ERROR(50000000, "SERVER_ERROR", "Server error!"),

    GRPC_ERROR(50000001, "GRPC_ERROR", "Grpc error!");

    public static final String DELIMITER = ":";

    private final int code;

    private final String name;

    private String text;

    private Throwable cause;

    YuntransStatus(int code, String name, String text) {
        this.code = code;
        this.name = name;
        this.text = text;
        this.cause = null;
    }

    YuntransStatus(int code, String name, String text, Throwable cause) {
        this.code = code;
        this.name = name;
        this.text = text;
        this.cause = cause;
    }

    public YuntransStatus modify(String text) {
        this.text=text;
        return this;
    }

    public YuntransStatus modify(String text, Throwable cause) {
        this.text = text;
        this.cause = cause;
        return this;
    }

    public YuntransStatus modify(Throwable cause) {
        this.cause = cause;
        return this;
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
