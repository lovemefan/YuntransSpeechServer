package com.yuntrans.websocketserver.model;

import lombok.Data;

/**
 * @Time : 2021/8/21 14:34
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Data
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