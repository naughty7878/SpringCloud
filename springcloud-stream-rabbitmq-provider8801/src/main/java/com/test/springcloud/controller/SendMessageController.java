package com.test.springcloud.controller;

import com.test.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;

    @RequestMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
