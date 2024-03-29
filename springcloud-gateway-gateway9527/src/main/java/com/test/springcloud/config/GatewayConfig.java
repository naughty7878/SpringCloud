package com.test.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // 自定义路由定位器
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        // 路由构造器
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // 设置路径
        routes.route("baidu_news_guonei_routh", r -> {
            return r.path("/guonei").uri("http://news.baidu.com/guonei");
        });
        routes.route("baidu_news_guoji_routh", r -> {
            return r.path("/guoji").uri("http://news.baidu.com/guoji");
        });

        return routes.build();
    }


}
