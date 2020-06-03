package com.test.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.test.springcloud.service.PaymentService;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") // 没有配置
//    @SentinelResource(value = "fallback", fallback = "handlerFallback") // fallback负责业务异常和限流返回
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHander只负责sentinel控制台配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException，该ID没有对应的记录，空指针异常");
        }

        return result;
    }

    public CommonResult<Payment> handlerFallback(Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(500, "兜底异常处理handlerFallback，Exception内容：" + e.getMessage(), payment);
    }

    public CommonResult<Payment> blockHandler(Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(500, "blockHandler-Sentinel限流，Exception内容：" + blockException.getMessage(), payment);
    }


    // =======OpenFeign
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return  paymentService.paymentSQL(id);
    }

}
