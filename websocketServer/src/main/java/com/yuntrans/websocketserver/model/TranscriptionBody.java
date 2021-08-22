package com.yuntrans.websocketserver.model;

/**
 * @Time : 2021/8/21 14:34
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public class TranscriptionBody {
    private String speechId;
    private String result;
    private TranscriptionStatus status;

}

enum TranscriptionStatus {
    PARTIAL,
    FINAL,
    ERROR,
    END
}