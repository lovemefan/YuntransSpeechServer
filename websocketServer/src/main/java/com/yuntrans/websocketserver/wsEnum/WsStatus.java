/**
 * @Time : 2021/8/12 16:09
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
package com.yuntrans.websocketserver.wsEnum;

public enum WsStatus {
    SUCCESS(10000),
    FAILED(10001),
    UNAUTHORIZED(10002),
    FORBIDDEN(10003),
    TIMEOUT(10004),
    ILLEGAL_CONNECTION(10005),
    FORMAT_ERROR(10016)
    ;


    private Integer statusCode;
    private WsStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    @Override
    public String toString() {
        return statusCode.toString();
    }
}
