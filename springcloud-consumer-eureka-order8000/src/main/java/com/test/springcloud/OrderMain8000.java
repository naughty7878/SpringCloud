package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyCustomeRule.class)
public class OrderMain8000 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain8000.class, args);
    }
}
