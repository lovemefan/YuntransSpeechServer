package com.yuntrans.websocketserver.handle;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Time : 2021/8/18 11:02
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public class WsManagement {
    //存放每个客户端对应的MyWebSocket对象
    private static ConcurrentHashMap<String, WebSocketSession> webSocketMap = new ConcurrentHashMap<String, WebSocketSession>();
    //当前在线连接数
    private static Integer onlineCount = 0;

    /**
     *
     * @return 返回所有的websocket session
     */
    public static ConcurrentHashMap<String, WebSocketSession> getWebSocketMap() {
        return webSocketMap;
    }

    /**
     * 添加一个session
     * @param sid session id
     * @param session session
     */
    public static void addWebSocketSession(String sid, WebSocketSession session) {
        webSocketMap.put(sid, session);
        onlineCount ++;
    }

    /**
     * 根据sid 删除session
     * @param sid session id
     */
    public static void deleteWebSocketSession(String sid) {
        webSocketMap.remove(sid);
        onlineCount --;
    }

    /**
     * 根据sid 和 session 删除session
     * @param sid session id
     * @param webSocketSession  session
     */
    public static void deleteWebSocketSession(String sid, WebSocketSession webSocketSession) {
        webSocketMap.remove(sid, webSocketSession);
    }


    public static Integer getOnlineCount() {
        return onlineCount;
    }
}
