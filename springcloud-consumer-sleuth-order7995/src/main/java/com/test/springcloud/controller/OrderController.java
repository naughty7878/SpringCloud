package com.test.springcloud.controller;

import brave.Tracer;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    // 默认注入的是DefaultTracer
    @Autowired
    private Tracer tracer;

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(HttpServletRequest request){
        String result = restTemplate.getForObject("http://CLOUD-SLEUTH-PROVIDER" + "/payment/zipkin", String.class);
        System.out.println("traceid ====== " +  tracer.currentSpan().context().traceIdString());
        return result;
    }

}
