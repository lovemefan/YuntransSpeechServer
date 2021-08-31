package com.yuntrans.websocketserver.service;


import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.model.TranscriptionBody;
import org.springframework.messaging.MessageChannel;

/**
 * @Time : 2021/8/18 15:51
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MQSenderService {
    public boolean send(MessageChannel messageChannel, SpeechBody message) throws Exception;
    public  boolean sendWithTags(MessageChannel messageChannel, SpeechBody msg, String tag) throws Exception;
    public  boolean sendWithKeys(MessageChannel messageChannel, SpeechBody msg, String key) throws Exception;

    public boolean sendChineseMessage(SpeechBody msg, String key) throws Exception;
    public boolean sendOtherMessage(SpeechBody msg, String key) throws Exception;

}
