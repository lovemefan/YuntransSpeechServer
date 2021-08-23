package com.yuntrans.chinesebackend.controller;

import com.yuntrans.chinesebackend.model.TranscriptionBody;
import com.yuntrans.chinesebackend.model.TranscriptionStatus;
import com.yuntrans.chinesebackend.service.impl.MQSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Time : 2021/8/23 10:34
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Controller
@RequestMapping("/transcription")
public class TestController {
    @Autowired
    private MQSenderServiceImpl mqSender;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) throws Exception {
        mqSender.send(new TranscriptionBody("123456", "this is a test transcription", TranscriptionStatus.FINAL));
        System.out.println("发送了一条消息");
        return "ok";
    }
}
