package com.test.springcloud.service;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{

    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(500, "服务降级返回，----PaymentFallbackService-paymentSQL");
    }
}
