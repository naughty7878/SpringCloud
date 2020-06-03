package com.test.springcloud;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
// 自动代理数据源
@EnableAutoDataSourceProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SeataOrder9011 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrder9011.class, args);
    }
}
