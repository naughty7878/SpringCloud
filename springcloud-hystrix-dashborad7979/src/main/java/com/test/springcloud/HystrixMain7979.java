package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

// 开启HystrixDashboard
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixMain7979 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMain7979.class, args);
    }
}
