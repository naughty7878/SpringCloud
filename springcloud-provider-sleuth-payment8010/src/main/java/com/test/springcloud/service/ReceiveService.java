package com.test.springcloud.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
//public class ReceiveService {
//
//    // 监听rabbitmq队列 "zipkin"
//    @RabbitListener(queues = "zipkin")
//    // 接受到对象
//    public void receive(Message message){
//        String messageBody = new String(message.getBody());
//        System.out.println("收到了消息是 : " + messageBody);
//    }
//}
