package com.yuntrans.websocketserver.model;

import com.google.gson.Gson;
import com.yuntrans.websocketserver.wsEnum.TranscriptionStatus;
import lombok.Data;

/**
 * @Time : 2021/8/21 14:34
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Data
public class TranscriptionBody {
    private String sid;
    private String result;
    private TranscriptionStatus status;

    public TranscriptionBody() {
    }

    public TranscriptionBody(String sid, String result, TranscriptionStatus status) {
        this.sid = sid;
        this.result = result;
        this.status = status;
    }

    public String toJson() {
        return "{"
                + "\"sid\": \"" + sid + "\","
                + "\"result\": \"" + result + "\","
                + "\"status\": \"" + status +"\""
                + "}";
    }
}
