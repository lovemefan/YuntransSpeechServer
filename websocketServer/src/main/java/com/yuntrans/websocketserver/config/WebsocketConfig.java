package com.yuntrans.websocketserver.config;

import com.yuntrans.websocketserver.handle.WsHandle;
import com.yuntrans.websocketserver.interceptor.WsAuthHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig  implements WebSocketConfigurer {

    @Autowired
    private WsAuthHandshakeInterceptor wsHandshakeInterceptor;

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
