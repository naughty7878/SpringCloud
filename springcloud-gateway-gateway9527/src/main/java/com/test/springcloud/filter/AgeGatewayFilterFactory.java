package com.test.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

// 继承AbstractNameValueGatewayFilterFactory且
// 我们的自定义名称必须要以GatewayFilterFactory结尾并交给spring管理。
// 自定义过滤器工厂
@Component
public class AgeGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        System.out.println("config.getName() = " + config.getName());
        System.out.println("config.getValue() = " + config.getValue());
//        int minAge = Integer.parseInt(config.getName());
//        int maxAge = Integer.parseInt(config.getValue());
        String[] arr = config.getValue().split(",");
        int minAge = Integer.parseInt(arr[0]);
        int maxAge = Integer.parseInt(arr[1]);
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                // TODO 获取请求参数age，判断是否满足[18, 60)
                MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
                String age = queryParams.getFirst("age");
                if (!StringUtils.isEmpty(age) && age.matches("[0-9]+")) {
                    int iAge = Integer.parseInt(age);
                    if (iAge >= minAge && iAge < maxAge) {
                         return  chain.filter(exchange);
                    }
                }

                ServerHttpResponse response = exchange.getResponse();
                byte[] bits = "请求参数 age 不满足[18, 60)".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                //指定编码，否则在浏览器中会中文乱码
                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
        };
    }
}
