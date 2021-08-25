package com.yuntrans.chinesebackend.service.impl;

import com.yuntrans.chinesebackend.service.AbsAsyncAsrEngineHandlerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @Time : 2021/8/25 15:00
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */

@Service
public class AsyncAseEngineHandlerService extends AbsAsyncAsrEngineHandlerService {
    @Value("${asrbackend.engineUri}")
    private String engineUri;

    @Async(value="asrHandlerThreadPool")
    @Override
    public void createTask(String sid) {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client, new TextWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                ClientManagement.addWsSessions(sid, session);
            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message)
                    throws Exception {
                System.out.println("当前线程id： " + Thread.currentThread().getId() + " receive: " + message.getPayload());
                super.handleTextMessage(session, message);
            }
            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                ClientManagement.removeWsSessions(sid);
                ClientManagement.removeMessageQueue(sid);
            }

        }, this.engineUri);
        manager.start();

        while(ClientManagement.getWebSocketSession(sid).isOpen()) {

        }
    }
}
