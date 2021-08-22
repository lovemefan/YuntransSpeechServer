package com.yuntrans.chinesebackend.service;

import com.yuntrans.chinesebackend.model.SpeechBody;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Time : 2021/8/18 15:53
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MQReceiveService {

    public void receive(SpeechBody message);
}
