/**
 * @ Time  :2021/8/12 15:29
 * @ Author :lovemefan
 * @ Email :lovemefan@outlook.com
 */
package com.yuntrans.websocketserver.interceptor;

import com.yuntrans.websocketserver.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;

import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Component
public class WsAuthHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${websocket.signature.enable:true}")
    private boolean isSignature;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        // 每个连接设置一个sid
        map.put("sid", "yuntrans@" + UUID.randomUUID().toString().replace("-", ""));

        StringJoiner signatureOraigin = new StringJoiner("\n");


        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();

        // 开启签名验证
        if (isSignature) {
            String date = request.getParameter("date");
            String appKey =  request.getParameter("appKey");
            String signatureClient = request.getParameter("signature");
            map.put("date", date);
            map.put("appKey", appKey);
            map.put("signature", signatureClient);

            if (date == null || appKey == null || signatureClient == null) {
                serverHttpResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return false;
            }


            signatureOraigin.add("date: " + date);
            signatureOraigin.add("appKey: " + appKey);
            signatureOraigin.add("GET " + serverHttpRequest.getURI().getRawPath());

            // 获取Redis中的数据
            String secret = redisTemplate.boundValueOps(appKey).get();

            if (secret == null) {
                log.info("appKey: " + appKey + " is empty");
                serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

            String signature = EncryptUtil.HmacSHA256Encrypt(signatureOraigin.toString(), secret);
            log.info("secret: " + secret + ",signature: " + signature);
            map.put("auth", signature.equals(signatureClient));
        }else {
            map.put("date", System.currentTimeMillis());
            map.put("auth", true);
        }


        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
