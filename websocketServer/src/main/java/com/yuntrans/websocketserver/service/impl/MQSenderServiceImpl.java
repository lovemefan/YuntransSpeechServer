package com.yuntrans.websocketserver.service.impl;

import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.model.TranscriptionBody;
import com.yuntrans.websocketserver.service.MQSenderService;
import com.yuntrans.websocketserver.service.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @Time : 2021/8/18 15:55
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */

@Service
public class MQSenderServiceImpl implements MQSenderService {

    @Autowired
    private MySource source;

    /**
     * 发送到 rocketmq
     * @param message message
     * @return boolean 是否发送成功
     * @throws Exception
     */
    @Override
    public boolean send(SpeechBody message) throws Exception {
        boolean flag= source.speechOutput().send(MessageBuilder.withPayload(message).build());
        return flag;
    }
}
