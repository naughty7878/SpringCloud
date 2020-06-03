package com.test.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
//允许断路器
@EnableCircuitBreaker
//@EnableHystrix
public class PaymentMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8008.class, args);
    }
}
