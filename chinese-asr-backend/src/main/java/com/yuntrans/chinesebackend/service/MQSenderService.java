package com.yuntrans.chinesebackend.service;

import com.yuntrans.chinesebackend.model.TranscriptionBody;

/**
 * @Time : 2021/8/18 15:51
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MQSenderService {
    public boolean send(TranscriptionBody message) throws Exception;
}
