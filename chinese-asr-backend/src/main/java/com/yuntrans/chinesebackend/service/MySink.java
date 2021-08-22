package com.yuntrans.chinesebackend.service;

import com.yuntrans.chinesebackend.model.SpeechBody;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @Time : 2021/8/19 11:24
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MySink {
    @Input("speech-zh-input")
    SubscribableChannel speechInput();
}
