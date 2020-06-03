package com.test.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @GetMapping("/order")
    public Map<String, Object> order(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "abc");
        return map;
    }
}
