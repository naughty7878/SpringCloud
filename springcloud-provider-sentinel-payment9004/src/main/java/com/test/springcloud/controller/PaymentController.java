package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();

    static {
        hashMap.put(1L, new Payment(1L, "aaaaaa"));
        hashMap.put(2L, new Payment(2L, "bbbbbb"));
        hashMap.put(3L, new Payment(3L, "cccccc"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<Payment>(200, "from server port : " + serverPort, payment);
        return result;
    }
}
