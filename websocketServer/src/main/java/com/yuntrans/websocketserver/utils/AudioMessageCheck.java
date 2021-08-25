package com.yuntrans.websocketserver.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.wsEnum.AudioStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Time : 2021/8/24 22:20
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public class AudioMessageCheck {

    private static final Gson gson = new Gson();
    public static SpeechBody check(TextMessage message) {
       String msg =  message.getPayload();

       try {
            SpeechBody speech = gson.fromJson(msg, SpeechBody.class);
            return speech;
       }catch (JsonSyntaxException e) {
           return null;
       }

    }
}
