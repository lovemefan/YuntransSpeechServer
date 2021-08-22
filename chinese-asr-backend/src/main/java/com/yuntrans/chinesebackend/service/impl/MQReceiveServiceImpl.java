package com.yuntrans.chinesebackend.service.impl;

import com.yuntrans.chinesebackend.model.SpeechBody;
import com.yuntrans.chinesebackend.service.MQReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @Time : 2021/8/19 11:07
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Slf4j
@Service
public class MQReceiveServiceImpl implements MQReceiveService {

    @StreamListener("speech-zh-input")
    @Override
    public void receive(@Payload SpeechBody speech) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), speech.toString());
    }
}
