package com.yuntrans.chinesebackend.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yuntrans.chinesebackend.model.TranscriptionBody;
import com.yuntrans.chinesebackend.model.TranscriptionStatus;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time : 2021/8/26 0:20
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public class JsonToTranscriptionBody {
    private static Gson gson = new Gson();

    public static TranscriptionBody parse(TextMessage message, String sid) {
        String json =   message.getPayload();
        JsonParser JsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) JsonParser.parse(json);

        // gson 的一个坑，字符串里面还有一层双引号，此处替换掉双引号
        if ("partial_result".equals(jsonObject.get("type").getAsString())) {
            String sentence = JsonParser.parse(jsonObject.get("nbest").getAsString().replace("\\\"", "")).getAsJsonArray().get(0).getAsJsonObject().get("sentence").getAsString();
            return new TranscriptionBody(sid, sentence, TranscriptionStatus.PARTIAL);
        } else if("final_result".equals(jsonObject.get("type").getAsString())){
            String sentence = JsonParser.parse(jsonObject.get("nbest").getAsString().replace("\\\"", "")).getAsJsonArray().get(0).getAsJsonObject().get("sentence").getAsString();
            return new TranscriptionBody(sid, sentence, TranscriptionStatus.FINAL);
        } else {
            return null;
        }
    }
}
