package com.test.springboot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.springboot.service.FlowLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @Autowired
    FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA(){
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        System.out.println(Thread.currentThread().getName() + "--------testB");
        return "--------testB";
    }

    @GetMapping("/testC")
    public String testC(){
        System.out.println(Thread.currentThread().getName() + "--------testC");
        return "--------testC";
    }

    @GetMapping("/testD")
    public String testD(){
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int n = 10/0;
        return "--------testD";
    }

    @GetMapping("/testHotKey")
    // 定义资源名称及处理方法
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false)  String p2){
        System.out.println(Thread.currentThread().getName() + "--------testHotKey");
        return "--------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException excetion){
        System.out.println(Thread.currentThread().getName() + "--------deal_testHotKey");
        return "--------deal_testHotKey";
    }

    @GetMapping("/testE")
    public String testE(){
        System.out.println(Thread.currentThread().getName() + "--------testE");
        return "--------testE";
    }

}
