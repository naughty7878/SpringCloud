package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//允许断路器
@EnableCircuitBreaker
//@EnableHystrix
public class PaymentMain8009 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8009.class, args);
    }
}
