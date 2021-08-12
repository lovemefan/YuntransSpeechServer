package com.yuntrans.websocketserver.config;

import com.yuntrans.websocketserver.handle.WsHandle;
import com.yuntrans.websocketserver.interceptor.WsHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebsocketConfig  implements WebSocketConfigurer {

    @Autowired
    private WsHandshakeInterceptor wsHandshakeInterceptor;

    @Autowired
    private WsHandle wsHandle;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(wsHandle, "/v1/api/asr")
                .addInterceptors(wsHandshakeInterceptor)
                .setAllowedOrigins("*");
    }
}
