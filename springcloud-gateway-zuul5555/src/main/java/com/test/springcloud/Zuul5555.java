package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// 启用Zuul网关
@EnableZuulProxy
@SpringBootApplication
public class Zuul5555 {
    public static void main(String[] args) {
        SpringApplication.run(Zuul5555.class, args);
    }
}
