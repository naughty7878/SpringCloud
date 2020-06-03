package com.test.springcloud;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableAutoDataSourceProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SeataStorage9012 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorage9012.class, args);
    }
}
