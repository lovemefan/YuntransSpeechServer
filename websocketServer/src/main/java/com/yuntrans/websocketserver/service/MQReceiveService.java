package com.yuntrans.websocketserver.service;


import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.model.TranscriptionBody;

/**
 * @Time : 2021/8/18 15:53
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MQReceiveService {

    public void receiveChinese(TranscriptionBody message);
    public void receiveOther(TranscriptionBody message);
}
