package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 开启Feign客户端
@EnableFeignClients
@SpringBootApplication
public class OrderMain7997 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain7997.class, args);
    }
}
