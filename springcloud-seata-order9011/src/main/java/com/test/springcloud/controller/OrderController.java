package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Order;
import com.test.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(1, "success");
    }
}
