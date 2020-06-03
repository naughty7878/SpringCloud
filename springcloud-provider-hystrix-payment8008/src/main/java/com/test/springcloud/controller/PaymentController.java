package com.test.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("result===" + result);
        return result;
    }


    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("result===" + result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String paymentInfo_circuit(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result===" + result);
        return result;
    }

    // 线程
    @GetMapping(value = "/payment/hystrix/thread/{id}")
    public String paymentCircuitBreakerThreadPool(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreakerThread(id);
        log.info("result===" + result);
        return result;
    }

    // 信号量
    @GetMapping(value = "/payment/hystrix/semaphore/{id}")
    public String paymentCircuitBreakerLimit(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreakerSemaphore(id);
        log.info("result===" + result);
        return result;
    }


}
