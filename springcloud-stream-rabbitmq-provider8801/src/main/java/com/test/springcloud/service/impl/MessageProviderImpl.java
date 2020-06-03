package com.test.springcloud.service.impl;

import com.test.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

// 定义消息的推送管道
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    // 消息发送通道
    private MessageChannel output;

    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("====serial: " + serial);
        return null;
    }
}
