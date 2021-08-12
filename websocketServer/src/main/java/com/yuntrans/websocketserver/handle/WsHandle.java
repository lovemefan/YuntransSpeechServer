/**
 * @Time : 2021/8/12 15:47
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
package com.yuntrans.websocketserver.handle;

import com.yuntrans.websocketserver.server.WebsocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.Session;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
public class WsHandle extends TextWebSocketHandler {

    //当前在线连接数
    private static Integer onlineCount = 0;
    //存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketSession> webSocketSet = new CopyOnWriteArraySet<WebSocketSession>();
    private WebSocketSession session;
    //接收sid
    private String sid = "";

    public WsHandle() {
        super();
    }


    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        this.session = session;
        webSocketSet.add(session);     //加入set中
        this.sid = "yuntrans@" + UUID.randomUUID().toString().replace("-", "");
        addOnlineCount();         //在线数加1
        try {
            handleTextMessage(session,new TextMessage("connect success"));
            log.info("有新窗口开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        session.sendMessage(message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        //从set中删除
        webSocketSet.remove(session);
        subOnlineCount();
        log.info("释放的sid为："+sid);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    private static void addOnlineCount() {
        onlineCount ++;
    }

    private static void subOnlineCount() {
        onlineCount --;
    }
    private static int getOnlineCount() {
        return onlineCount;
    }
}
