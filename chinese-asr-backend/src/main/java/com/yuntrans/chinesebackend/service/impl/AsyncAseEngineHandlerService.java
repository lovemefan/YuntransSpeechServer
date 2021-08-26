package com.yuntrans.chinesebackend.service.impl;

import com.yuntrans.chinesebackend.model.TranscriptionBody;
import com.yuntrans.chinesebackend.service.AbsAsyncAsrEngineHandlerService;
import com.yuntrans.chinesebackend.service.MQSenderService;
import com.yuntrans.chinesebackend.utils.JsonToTranscriptionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

/**
 * @Time : 2021/8/25 15:00
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */

@Service
public class AsyncAseEngineHandlerService extends AbsAsyncAsrEngineHandlerService {
    @Value("${asrbackend.engineUri}")
    private String engineUri;
    @Autowired
    private MQSenderService mqSenderService;

    @Async(value="asrHandlerThreadPool")
    @Override
    public void createTask(String sid) {
        StandardWebSocketClient client = new StandardWebSocketClient();

        CountDownLatch wsConnection = new CountDownLatch(1);

        WebSocketConnectionManager manager = new WebSocketConnectionManager(client, new TextWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                ClientManagement.addWsSessions(sid, session);
                ClientManagement.addMessageQueue(sid);
                // 发送开始指令
                session.sendMessage(new TextMessage("{\n" +
                        "            \"signal\": \"start\",\n" +
                        "            \"nbest\": 1,\n" +
                        "            \"continuous_decoding\": true\n" +
                        "          }"));
                wsConnection.countDown();

            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message)
                    throws Exception {
                System.out.println("当前处理text线程id： " + Thread.currentThread().getId() + " message receive: " + message.getPayload());

                super.handleTextMessage(session, message);
                TranscriptionBody transcription = JsonToTranscriptionBody.parse(message, sid);
                if (transcription != null) {
                    System.out.println("当前处理text线程id： " + Thread.currentThread().getId() + " transcription receive: " + transcription.toString());
                    mqSenderService.sendWithKeys(transcription, sid);
                }
            }
            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                ClientManagement.removeWsSessions(sid);
                ClientManagement.removeMessageQueue(sid);
            }

        }, this.engineUri);
        manager.start();

        try {
            wsConnection.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前监听sid队列线程id： " + Thread.currentThread().getId());
        // 监听当前sid 队列
        while(ClientManagement.getWebSocketSession(sid) != null && ClientManagement.getWebSocketSession(sid).isOpen()) {
            try {
                WebSocketSession session = ClientManagement.getWebSocketSession(sid);
                // 获取队列中的元素，为空时阻塞
                System.out.println("发送数据中");
                byte[] data = Base64.getDecoder().decode(ClientManagement.getMessageFromQueue(sid).getData());
                if (data != null) {
                    session.sendMessage(new BinaryMessage(data));
                }
            }catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("session 已断开");
    }
}
