package com.test.springcloud.controller;

import com.test.springcloud.config.LoadBalancer;
import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

////    @Autowired
//    // 未使用Ribbon包装restTemplate
//    private RestTemplate restTemplate = new RestTemplate();


    @PostMapping("/consumer/payment/insert")
    public CommonResult<Payment> insert(Payment payment){
        log.info("====== 请求插入：" + payment);
        return restTemplate.postForObject(PAYMENT_URL + "/payment/insert", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}/lb")
    public CommonResult<Payment> getPaymentLB(@PathVariable("id") Long id){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() == 0) {
            return null;
        }

        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/feign/timeout", String.class);
    }
}
