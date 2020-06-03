package com.test.springcloud.controller;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private Tracer tracer;

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(HttpServletRequest request){

        log.info("Hi, this is payment zipkin server");
        System.out.println(getHeadersInfo(request));
        System.out.println("traceid ====== " +  tracer.currentSpan().context().traceIdString());
        return "Hi, this is payment zipkin server";
    }

    // 获取Header头中的信息
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + "\t======>\t" + value);
            map.put(key, value);
        }
        return map;
    }
}
