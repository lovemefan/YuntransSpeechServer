package com.yuntrans.chinesebackend.service.impl;



import com.yuntrans.chinesebackend.model.TranscriptionBody;
import com.yuntrans.chinesebackend.service.MQSenderService;
import com.yuntrans.chinesebackend.service.MySource;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean send(TranscriptionBody message) throws Exception {
        boolean flag = source.transcriptionOutput().send(MessageBuilder.withPayload(message).build());
        return flag;
    }

    @Override
    public  boolean sendWithTags(TranscriptionBody msg, String tag) throws Exception {
        Message<TranscriptionBody> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        boolean flag = source.transcriptionOutput().send(message);

        return flag;
    }

}
