package com.yuntrans.chinesebackend.service;

import com.yuntrans.chinesebackend.model.SpeechBody;
import com.yuntrans.chinesebackend.model.TranscriptionBody;

/**
 * @Time : 2021/8/18 15:51
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MQSenderService {
    public boolean send(TranscriptionBody message) throws Exception;
    public  boolean sendWithTags(TranscriptionBody msg, String tag) throws Exception;
    public boolean sendWithKeys(TranscriptionBody msg, String key) throws Exception;

}
