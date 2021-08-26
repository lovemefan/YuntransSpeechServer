package com.yuntrans.chinesebackend.service.impl;

import com.yuntrans.chinesebackend.model.SpeechBody;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Time : 2021/8/25 14:29
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */

@Component
public class ClientManagement {
    //存放每个客户端对应的阻塞消息队列
    private static ConcurrentHashMap<String, LinkedBlockingQueue<SpeechBody>> messageQueue = new ConcurrentHashMap<String, LinkedBlockingQueue<SpeechBody>>();
    private static ConcurrentHashMap<String, WebSocketSession> wsSessions = new ConcurrentHashMap<String, WebSocketSession >();

    public static ConcurrentHashMap<String, LinkedBlockingQueue<SpeechBody>> getMessageQueue() {
        return messageQueue;
    }

    /**
     * 向某一个队列添加数据
     * @param sid 客户端id
     * @param speech speech 数据
     * @throws InterruptedException
     */
    public static void addMessageQueue(String sid, SpeechBody speech) throws InterruptedException {
        if (messageQueue.containsKey(sid)) {
            messageQueue.get(sid).put(speech);
        }else {
            LinkedBlockingQueue<SpeechBody> queue = new LinkedBlockingQueue<SpeechBody>();
            queue.put(speech);
            messageQueue.put(sid, queue);
        }

    }

    public static void addMessageQueue(String sid) {
        if (!messageQueue.containsKey(sid)) {
            messageQueue.put(sid, new LinkedBlockingQueue<SpeechBody>());
        }
    }

    /**
     *  根据sid 返回队列末尾元素, 会阻塞
     * @param sid
     * @return
     */
    public static SpeechBody getMessageFromQueue(String sid) throws InterruptedException {
        if (messageQueue.containsKey(sid)) {
            return messageQueue.get(sid).take();
        }else {
           return null;
        }
    }
    public static LinkedBlockingQueue<SpeechBody> getQueueBySid(String sid) {
        return messageQueue.get(sid);
    }

    /**
     * 根据sid 删除某一个队列
     * @param sid
     * @throws InterruptedException
     */
    public static void removeMessageQueue(String sid) throws InterruptedException {
        messageQueue.remove(sid);
    }

    public static ConcurrentHashMap<String, WebSocketSession> getWsSessions() {
        return wsSessions;
    }

    public static void addWsSessions(String sid, WebSocketSession sessions) {
        wsSessions.put(sid, sessions);
    }

    public static WebSocketSession getWebSocketSession(String sid) {
        return wsSessions.get(sid);
    }

    public static void removeWsSessions(String sid) {
        wsSessions.remove(sid);
    }

    public static void sendMessage(WebSocketSession session, String data) throws IOException {
        session.sendMessage(new TextMessage(data));
    }

    public static void sendMessage(WebSocketSession session, byte[] data) throws IOException {
        session.sendMessage(new BinaryMessage(data));
    }

    public static void closedWebsocket(String sid) throws IOException {
        WebSocketSession session = getWebSocketSession(sid);
        if (session != null) {
            session.sendMessage(new TextMessage("{ \"signal\": \"end\" }"));
        }
    }

    public static int getQueueCount() {
        return messageQueue.size();
    }
}
