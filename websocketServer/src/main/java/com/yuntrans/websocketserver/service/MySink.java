package com.yuntrans.websocketserver.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Time : 2021/8/19 11:24
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface MySink {
    @Input("transcription-zh-input")
    SubscribableChannel transcriptionInput();

    @Input("transcription-other-input")
    SubscribableChannel transcriptionOtherInput();
}
