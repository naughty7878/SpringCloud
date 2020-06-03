package com.test.springboot.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public static String handleException(BlockException exception) {
        return "自定义的限流处理信息-------CustomerBlockHandler-handleException";
    }

    public static String handleException2(BlockException exception) {
        return "自定义的限流处理信息-------CustomerBlockHandler-handleException2";
    }
}
