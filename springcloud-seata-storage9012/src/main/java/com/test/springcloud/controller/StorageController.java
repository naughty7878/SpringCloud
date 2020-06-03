package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(1, "success");
    }

}
