/**
 * @ Time  :2021/8/12 15:29
 * @ Author :lovemefan
 * @ Email :lovemefan@outlook.com
 */
package com.yuntrans.websocketserver.interceptor;

import com.yuntrans.websocketserver.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class WsAuthHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        StringJoiner signatureOraigin = new StringJoiner("\n");

        String ip = serverHttpRequest.getHeaders().getFirst("x-forwarded-for");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            log.info(request.toString());
            System.out.println(request.getParameter("date"));
            System.out.println(request.getParameter("appkey"));
            System.out.println(request.getParameter("signature"));

            map.put("date", request.getParameter("date"));
            map.put("appkey", request.getParameter("appkey"));
            map.put("signature", request.getParameter("signature"));
        }

        signatureOraigin.add("host: " + serverHttpRequest.getRemoteAddress().toString());
        signatureOraigin.add("date: " + map.get("date").toString());
        signatureOraigin.add("appkey: " + map.get("date").toString());
        signatureOraigin.add("GET: " + serverHttpRequest.getURI().getRawPath());

        log.debug(signatureOraigin.toString());
        String signature_sha = EncryptUtil.HmacSHA256Encrypt(signatureOraigin.toString(), "2kCPFNALTgPbi9GIzOTCw1bPkvsjhwI9gsMKoRocKW8=");
        String signature = Base64.getEncoder().encodeToString(signature_sha.getBytes());

        return signature.equals(map.get("signature"));
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
