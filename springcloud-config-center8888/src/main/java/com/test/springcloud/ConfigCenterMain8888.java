package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// 激活配置中心
@EnableConfigServer
@SpringBootApplication
public class ConfigCenterMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain8888.class, args);
    }
}
