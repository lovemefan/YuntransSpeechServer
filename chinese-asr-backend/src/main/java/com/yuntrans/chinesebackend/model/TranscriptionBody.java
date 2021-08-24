package com.yuntrans.chinesebackend.model;

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

    public TranscriptionBody(String sid, String result, TranscriptionStatus status) {
        this.sid = sid;
        this.result = result;
        this.status = status;
    }
}

