package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/insert")
    public CommonResult insert(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        log.info("====== 插入结果：" + result);
        if(result > 0) {
            return new CommonResult(200, "插入数据成功，服务端口：" + serverPort);
        }else {
            return new CommonResult(500, "插入数据失败");
        }

    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);

        log.info("====== 查询结果：" + result);
        if(result != null) {
            return new CommonResult(200, "查询成功，服务端口：" + serverPort, result);
        }else {
            return new CommonResult(500, "查询失败");
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        // 获取服务列表
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("=====element:" + element);
        }

        // 获取服务实例集
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务发现" + "\n"
                    + "getServiceId === " + instance.getServiceId() + "\n"
                    + "getHost === " + instance.getHost() + "\n"
                    + "getPort === " + instance.getPort() + "\n"
                    + "getUri === " + instance.getUri() );
        }

        return  this.discoveryClient;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
