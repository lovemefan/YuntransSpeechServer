package com.yuntrans.websocketserver.handle;

import com.yuntrans.websocketserver.model.ResponseBody;
import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.service.impl.MQSenderServiceImpl;
import com.yuntrans.websocketserver.utils.AudioMessageCheck;
import com.yuntrans.websocketserver.wsEnum.AudioStatus;
import com.yuntrans.websocketserver.wsEnum.WsStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Time : 2021/8/12 15:47
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Slf4j
@Component
public class WsHandle extends TextWebSocketHandler {

    private WebSocketSession session;


    @Autowired
    private MQSenderServiceImpl mqSender;

    @Value("${websocket.timeout-time:600000}")
    private Integer timeout;

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


        boolean auth = Boolean.parseBoolean(session.getAttributes().get("auth").toString());
        long date = Long.parseLong(session.getAttributes().get("date").toString());


        //加入set中
        WsManagement.addWebSocketSession(session.getAttributes().get("sid").toString(), session);


        //授权失败
        if (!auth) {
            this.sendMessage(WsStatus.UNAUTHORIZED, "Signature Illegal", session.getAttributes().get("sid").toString());
            session.close(CloseStatus.NOT_ACCEPTABLE);
        }else if (System.currentTimeMillis() - date > timeout) {
            // 连接超时，应该是客户端抓包后请求
            this.sendMessage(WsStatus.ILLEGAL_CONNECTION, "Illegal Connection", session.getAttributes().get("sid").toString());
            session.close(CloseStatus.NOT_ACCEPTABLE);
        }else{

            try {
                // 发送建立连接信息
                mqSender.send(new SpeechBody(null, null, AudioStatus.START, session.getAttributes().get("sid").toString(), null));

                this.sendMessage(WsStatus.SUCCESS, "Connection success", session.getAttributes().get("sid").toString());
                log.info("当前线程" + Thread.currentThread().getId() +  "有新窗口开始监听:" + session.getAttributes().get("sid").toString() + ",当前在线人数为:" + WsManagement.getOnlineCount());
            } catch (IOException e) {
                log.error("websocket IO Exception");
            }
        }

    }

    public void sendMessage(WebSocketSession session, String data) throws IOException {
        session.sendMessage(new TextMessage(data));
    }

    public void sendMessage(String data) throws IOException {
        this.session.sendMessage(new TextMessage(data));
    }
    public void sendMessage(ResponseBody responseBody) throws IOException {
        this.session.sendMessage(new TextMessage(responseBody.toString()));
    }

    public void sendMessage(WsStatus status, String message, String sid) throws IOException {
        this.sendMessage(status, message, null, sid);
    }

    public void sendMessage(WsStatus status, String message, String data, String sid) throws IOException {
        this.session.sendMessage(
                new TextMessage(
                        new ResponseBody(
                                sid,
                                status.name(),
                                status.getStatusCode(),
                                message,
                                data
                        ).toString()));
    }

    public void sendMessage(WebSocketSession session, TextMessage data) throws IOException {
        session.sendMessage(data);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (session.isOpen()) {
            super.handleTextMessage(session, message);
//            session.sendMessage(message);
            SpeechBody speech = AudioMessageCheck.check(message);

            if (speech != null) {
                System.out.println(Thread.currentThread().getName() + " 收到speech： " + session.getAttributes().get("sid").toString());
                // 设置sid
                speech.setSid(session.getAttributes().get("sid").toString());
                mqSender.sendWithKeys(speech, session.getAttributes().get("sid").toString());
            }else {
                // json 反序列失败
                sendMessage(WsStatus.FORMAT_ERROR, "Message must be json format", session.getAttributes().get("sid").toString());
            }
        }

    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        //从set中删除
        WsManagement.deleteWebSocketSession(session.getAttributes().get("sid").toString());
        // 发送连接断开信息
        mqSender.sendWithKeys(new SpeechBody(null, null, AudioStatus.END, session.getAttributes().get("sid").toString(), null), session.getAttributes().get("sid").toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        //从set中删除
        WsManagement.deleteWebSocketSession(session.getAttributes().get("sid").toString());
        // 发送连接断开信息
        mqSender.sendWithKeys(new SpeechBody(null, null, AudioStatus.END, session.getAttributes().get("sid").toString(), null), session.getAttributes().get("sid").toString());

        log.info("释放的sid为："+ session.getAttributes().get("sid").toString());
        log.info("" + WsManagement.getWebSocketMap().isEmpty());
        log.info("有一连接关闭！当前在线人数为" + WsManagement.getOnlineCount());
    }
}
