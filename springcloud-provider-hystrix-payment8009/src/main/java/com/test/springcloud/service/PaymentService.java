package com.test.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName()
                + "，paymentInfo_OK，ID == " + id;
    }


    // fallbackMethod: 设置HystrixCommand服务降级所使用的方法名称，注意该方法需要与原方法定义在同一个类中，并且方法签名也要一致
    // commandProperties: 设置HystrixCommand属性，如：断路器失败百分比、断路器时间容器大小等
    // 设置断路器超时降级策略，时间3000毫秒超时
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int second = 5000;
        try {
            // 休眠5000毫秒
            TimeUnit.MILLISECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 异常
//        int n = 10/0;
        return "线程池：" + Thread.currentThread().getName()
                + "，paymentInfo_Timeout，ID == " + id
                + "，耗时" + second + "毫秒";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        String result = "线程池：" + Thread.currentThread().getName()
                + "，paymentInfo_TimeoutHandler，ID == " + id;
        return result;
    }



    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
            // 属性设置参考：HystrixCommandProperties
            commandProperties = {
                    // 是否开启断路器，默认true
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    // 请求次数，默认20
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    // 时间窗口期，默认5000
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    // 失败率到达多少后跳闸，默认50
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0) {
            throw  new RuntimeException("======== id 不能为负数 =========");
        }
        String serialNumber = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return " id 不能为负数，请稍后再试， id == " + id;
    }


    // 服务限流
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Semaphore",
            // 属性设置参考：HystrixCommandProperties
            commandProperties = {
                    // 隔离策略，有THREAD和SEMAPHORE
                    // THREAD - 它在单独的线程上执行，并发请求受线程池中的线程数量的限制（默认）
                    // SEMAPHORE - 它在调用线程上执行，并发请求受到信号量计数的限制
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
                    // 设置在使用时允许到HystrixCommand.run（）方法的最大请求数。默认值：10 ，SEMAPHORE模式有效
                    @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="1")

            })
    public String paymentCircuitBreakerSemaphore(@PathVariable("id") Integer id){
        int second = 500;
        try {
            // 休眠500毫秒
            TimeUnit.MILLISECONDS.sleep(second);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        // 异常
//        int n = 10/0;
        String result = "线程池：" + Thread.currentThread().getName()
                + "，paymentCircuitBreaker_Semaphore，ID == " + id
                + "，耗时" + second + "毫秒";
        return result;
    }

    public String paymentCircuitBreaker_Semaphore(@PathVariable("id") Integer id){
        return " paymentCircuitBreaker_Semaphore 服务限流，ID == " + id;
    }



    // 服务限流
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Thread",
            // 属性设置参考：HystrixCommandProperties
            commandProperties = {
                    // 隔离策略，有THREAD和SEMAPHORE
                    @HystrixProperty(name="execution.isolation.strategy", value="THREAD")
            },
            threadPoolProperties = {
                    // 线程池核心线程数
                    @HystrixProperty(name = "coreSize", value = "3"),
                    // 队列最大长度
                    @HystrixProperty(name = "maxQueueSize", value = "5"),
                    // 排队线程数量阈值，默认为5，达到时拒绝，如果配置了该选项，队列的大小是该队列
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "7")
            })
    public String paymentCircuitBreakerThread(@PathVariable("id") Integer id){
        int second = 500;
        try {
            // 休眠500毫秒
            TimeUnit.MILLISECONDS.sleep(second);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        // 异常
//        int n = 10/0;
        String result = "线程池：" + Thread.currentThread().getName()
                + "，paymentCircuitBreakerThreadPool，ID == " + id
                + "，耗时" + second + "毫秒";
        return result;
    }

    public String paymentCircuitBreaker_Thread(@PathVariable("id") Integer id){
        return " paymentCircuitBreaker_Thread 服务限流，ID == " + id;
    }
}
