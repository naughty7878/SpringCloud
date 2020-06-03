package com.test.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    // 筛选出服务
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
