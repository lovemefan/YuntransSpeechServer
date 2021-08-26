package com.yuntrans.chinesebackend.service.impl;

import com.yuntrans.chinesebackend.model.SpeechBody;
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

        AsyncAseEngineHandlerService that = this;

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

                super.handleTextMessage(session, message);
                TranscriptionBody transcription = JsonToTranscriptionBody.parse(message, sid);
                if (transcription != null) {
                    mqSenderService.sendWithKeys(transcription, sid);
                }
            }
            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                System.out.println("当前队列数量： " + ClientManagement.getQueueCount());
                ClientManagement.addMessageQueue(sid, new SpeechBody());
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


        // 监听当前sid 队列
        while(ClientManagement.getWebSocketSession(sid) != null && ClientManagement.getWebSocketSession(sid).isOpen()) {
            try {
                WebSocketSession session = ClientManagement.getWebSocketSession(sid);

                SpeechBody speech = ClientManagement.getMessageFromQueue(sid);

                // 防止该线程被永久阻塞, 结束时会向该队列发送一个内容全为null的speechBody
                if (speech.getStatus() == null) {break;}

                byte[] data = Base64.getDecoder().decode(speech.getData());
                    System.out.println("当前处理text线程id： " + Thread.currentThread().getName() + " " + speech.getSid() + " 发送完毕 " );
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
