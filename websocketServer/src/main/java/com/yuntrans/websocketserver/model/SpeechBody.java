package com.yuntrans.websocketserver.model;

import com.yuntrans.websocketserver.wsEnum.AudioStatus;
import lombok.Data;

/**
 * @Time : 2021/8/21 14:34
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */

@Data
public class SpeechBody {
    // 语言代码
    private String languageCode;
    // 音频格式
    private String format;
    // 当前语音片段
    private AudioStatus status;
    // 语音片段id
    private String speechId;
    // 语音数据的base64编码
    private String data;

    public SpeechBody(String languageCode, String format, AudioStatus status, String speechId, String data) {
        this.languageCode = languageCode;
        this.format = format;
        this.status = status;
        this.speechId = speechId;
        this.data = data;
    }
}
