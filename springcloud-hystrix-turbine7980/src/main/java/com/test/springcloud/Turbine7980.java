package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

// 启用Turbine服务
@EnableTurbine
@SpringBootApplication
public class Turbine7980 {
    public static void main(String[] args) {
        SpringApplication.run(Turbine7980.class, args);
    }
}
