package com.test.springboot.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class FlowLimitService {

    @SentinelResource("message")
    public void message() {
        System.out.println("message");
    }
}
