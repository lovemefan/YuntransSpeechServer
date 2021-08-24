package com.yuntrans.websocketserver.service;


import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.model.TranscriptionBody;

/**
 * @Time : 2021/8/18 15:51
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MQSenderService {
    public boolean send(SpeechBody message) throws Exception;
    public  boolean sendWithTags(SpeechBody msg, String tag) throws Exception;
}
