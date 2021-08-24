package com.yuntrans.websocketserver.controller;

import com.yuntrans.websocketserver.model.SpeechBody;
import com.yuntrans.websocketserver.wsEnum.AudioStatus;
import com.yuntrans.websocketserver.service.impl.MQSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/asr")
public class WebsocketController {

    @Autowired
    private MQSenderServiceImpl mqSender;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) throws Exception {
        mqSender.send(new SpeechBody("zh", "wav", AudioStatus.START, "" + System.currentTimeMillis()/100, "something"));
        System.out.println("发送了一条消息");
        return "ok";
    }


    @RequestMapping(value = "/test_tag", method = RequestMethod.GET)
    @ResponseBody
    public String testTag(HttpServletRequest request) throws Exception {
        mqSender.sendWithTags(new SpeechBody("zh", "wav", AudioStatus.START, "" + System.currentTimeMillis()/100, "something with tag"), "zh0456789");
        System.out.println("发送了一条消息");
        return "ok";
    }
}
