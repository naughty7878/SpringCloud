package com.test.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService——》paymentInfo_OK——》统一处理：" + id;
    }

    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService——》paymentInfo_Timeout——》统一处理：" + id;
    }
}
