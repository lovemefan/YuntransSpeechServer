/**
 * @Time : 2021/8/12 16:02
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
package com.yuntrans.websocketserver.model;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class ResponseBody<T> {
    // session id
    private String sid;
    //status状态值：代表本次请求response的状态结果。
    private String status;
    private Integer statusCode;
    //response描述：对本次状态码的描述。
    private String message;
    //data数据：本次返回的数据。
    private T data;

    private static final Gson gson = new Gson();

    public ResponseBody(String status, Integer statusCode, String message, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
    public ResponseBody(String sid, String status, Integer statusCode, String message, T data) {
        this.sid = sid;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
