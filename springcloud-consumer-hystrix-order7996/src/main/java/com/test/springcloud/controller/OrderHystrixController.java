package com.test.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentClobalFallbackMethod")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("result===" + result);
        return result;
    }

//    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
//        String result = paymentHystrixService.paymentInfo_Timeout(id);
//        log.info("result===" + result);
//        return result;
//    }

//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    // @HystrixCommand 表名服务降级
    @HystrixCommand
    public String paymentTimeout(@PathVariable("id") Integer id) {
        int n = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("result===" + result);
        return result;
    }

    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id) {
        String result = "消费者：对方支付系统繁忙，请稍后再试，ID == " + id;
        log.info("result===" + result);
        return result;
    }


    // Global fallback
    public String paymentClobalFallbackMethod(){
        return "Global异常处理信息，请稍后再试";
    }
}
