package com.test.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
// openfeign客户端
// fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，
// fallback指定的类必须实现@FeignClient标记的接口
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
