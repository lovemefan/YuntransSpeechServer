package com.yuntrans.chinesebackend;

import com.yuntrans.chinesebackend.service.MySink;
import com.yuntrans.chinesebackend.service.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding({MySink.class, MySource.class})
public class ChineseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChineseBackendApplication.class, args);
    }

}
