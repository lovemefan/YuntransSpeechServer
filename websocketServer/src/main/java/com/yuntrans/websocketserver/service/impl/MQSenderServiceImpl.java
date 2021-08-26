package com.yuntrans.websocketserver.service.impl;

import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.model.TranscriptionBody;
import com.yuntrans.websocketserver.service.MQSenderService;
import com.yuntrans.websocketserver.service.MySource;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

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

    @Override
    public boolean sendWithKeys(SpeechBody msg, String key) throws Exception {
        Message<SpeechBody> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_KEYS, key)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        return source.speechOutput().send(message);
    }

    @Override
    public  boolean sendWithTags(SpeechBody msg, String tag) throws Exception {
        Message<SpeechBody> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        return source.speechOutput().send(message);
    }
}
