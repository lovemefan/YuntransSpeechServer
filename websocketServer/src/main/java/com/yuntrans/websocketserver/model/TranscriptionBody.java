package com.yuntrans.websocketserver.model;

import com.yuntrans.websocketserver.wsEnum.TranscriptionStatus;
import lombok.Data;

/**
 * @Time : 2021/8/16 20:40
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Data
public class TranscriptionBody {

    // 识别文本结果
    private String result;
    private String speechId;
    // 识别结果的状态
    private TranscriptionStatus status;
    // 识别结果的说明
    private String message;
}
