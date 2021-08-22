package com.yuntrans.chinesebackend.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Time : 2021/8/19 11:25
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */

public interface MySource {
    @Output("transcription-zh-output")
    MessageChannel transcriptionOutput();
}
