package com.yuntrans.websocketserver.service.impl;


import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.model.TranscriptionBody;
import com.yuntrans.websocketserver.service.MQReceiveService;
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

    @StreamListener("transcription-zh-input")
    @Override
    public void receive(@Payload TranscriptionBody transcription) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), transcription.toString());
    }
}
