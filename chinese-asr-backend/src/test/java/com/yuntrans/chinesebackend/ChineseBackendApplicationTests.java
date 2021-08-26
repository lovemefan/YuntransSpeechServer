package com.yuntrans.chinesebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChineseBackendApplicationTests {

    @Test
    void contextLoads() {
    }


    @Value("${asrbackend.asrHandler.corePoolSize:5}")
    private Integer asrHandlerCorePoolSize;

    @Value("${asrbackend.asrHandler.maxPoolSize:10}")
    private Integer asrHandlerMaxPoolSize;

    @Value("${asrbackend.asrSender.corePoolSize:5}")
    private Integer asrSenderCorePoolSize;

    @Value("${asrbackend.asrSender.maxPoolSize:10}")
    private Integer asrSenderMaxPoolSize;
    @Test
    void textAutowiredValue() {
        System.out.println("asrHandlerCorePoolSize: " + asrHandlerCorePoolSize);
        System.out.println("asrHandlerMaxPoolSize: " + asrHandlerMaxPoolSize);
;
    }

}
