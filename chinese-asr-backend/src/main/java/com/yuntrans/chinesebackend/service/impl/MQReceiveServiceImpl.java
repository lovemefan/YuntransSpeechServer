package com.yuntrans.chinesebackend.service.impl;

import com.yuntrans.chinesebackend.model.AudioStatus;
import com.yuntrans.chinesebackend.model.SpeechBody;
import com.yuntrans.chinesebackend.model.TranscriptionBody;
import com.yuntrans.chinesebackend.model.TranscriptionStatus;
import com.yuntrans.chinesebackend.service.MQReceiveService;
import com.yuntrans.chinesebackend.service.MQSenderService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Time : 2021/8/19 11:07
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Slf4j
@Service
public class MQReceiveServiceImpl implements MQReceiveService {
    @Autowired
    private AsyncAseEngineHandlerService asyncAseEngineHandlerService;
    @Autowired
    private MQSenderService mqSenderService;


    /**
     * reactor ,负责分发接收到的语音数据，根据sid放到不同的队列, 并启动消费线程
     * @param speech
     */
    @StreamListener("speech-zh-input")
    @Override
    public void receive(@Payload SpeechBody speech) {
        // 负责根据sid 分发
        if (speech.getStatus() == AudioStatus.START) {
            //启动消费线程
            asyncAseEngineHandlerService.createTask(speech.getSid());
        }else if (speech.getStatus() == AudioStatus.END) {
            try {
                ClientManagement.closedWebsocket(speech.getSid());
                ClientManagement.removeWsSessions(speech.getSid());
                ClientManagement.removeMessageQueue(speech.getSid());
            } catch (IOException | InterruptedException e) {
                try {
                    mqSenderService.send(new TranscriptionBody(speech.getSid(), null, TranscriptionStatus.ENGINE_ERROR));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (NullPointerException e2) {
                try {
                    e2.printStackTrace();
                    mqSenderService.send(new TranscriptionBody(speech.getSid(), null, TranscriptionStatus.ENGINE_NOT_WORK));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }else {
            try {
                ClientManagement.addMessageQueue(speech.getSid(), speech);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
