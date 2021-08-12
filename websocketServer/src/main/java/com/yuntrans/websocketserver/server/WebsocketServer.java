package com.yuntrans.websocketserver.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@Service
@ServerEndpoint("/vi/api/asr")
public class WebsocketServer  {
    //当前在线连接数
    private static int onlineCount = 0;
    //存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebsocketServer> webSocketSet = new CopyOnWriteArraySet<WebsocketServer>();

    private Session session;

    //接收sid
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     * @param session 当前session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        this.sid = UUID.randomUUID().toString().replace("-", "");
        addOnlineCount();           //在线数加1
        try {
            sendMessage("conn_success");
            log.info("有新窗口开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1

        log.info("释放的sid为："+sid);

        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        //群发消息
        for (WebsocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebsocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
