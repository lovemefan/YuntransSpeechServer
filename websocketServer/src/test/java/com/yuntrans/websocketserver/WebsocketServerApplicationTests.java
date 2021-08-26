package com.yuntrans.websocketserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebsocketServerApplicationTests {
    @Value("${websocket.signature.enable:true}")
    private boolean isSignature;
    @Test
    void contextLoads() {

        System.out.println(isSignature);
    }

}
