package com.yuntrans.chinesebackend.service.impl;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Time : 2021/8/19 10:55
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public interface Channel {
    String OUTPUT = "output";   //这里的名称对应spring.cloud.stream.rocketmq.bindings.<channelName>


    MessageChannel output();
}
