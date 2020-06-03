package com.test.springboot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.springboot.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public String byResource(){
        return "按资源名称限流测试OK";
    }

    public String handleException(BlockException exception){
        return exception.getClass().getCanonicalName() + "\t 服务不可用";
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public String byUrl(){
        return "按URL限流测试OK";
    }

    @GetMapping("/rateLimit/customerBlockHandker")
    @SentinelResource(value = "customerBlockHandker", blockHandlerClass = {CustomerBlockHandler.class}, blockHandler = "handleException")
    public String customerBlockHandker(){
        return "按客户自定义，限流异常处理";
    }
}
