package com.test.springcloud.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

// 自定义路由断言工厂
// @Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {


    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    // 将配置文件中的值按返回集合的顺序，赋值给配置类
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(new String[]{"minAge", "maxAge"});
    }

    @Override
    public Predicate<ServerWebExchange> apply(Consumer<Config> consumer) {
        return super.apply(consumer);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        // 创建网关断言对象
        return new Predicate<ServerWebExchange>() {
            // 检查
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // TODO 获取请求参数age，判断是否满足[18, 60)
                MultiValueMap<String, String> queryParams = serverWebExchange.getRequest().getQueryParams();
                String age = queryParams.getFirst("age");
                if (!StringUtils.isEmpty(age) && age.matches("[0-9]+")) {
                    int iAge = Integer.parseInt(age);
                    if (iAge >= config.minAge && iAge < config.maxAge) {
                        return true;
                    }
                }
                return false;
            }
        };
    }


    // 配置类，属性用于接收配置文件中的值
    @Validated
    public static class Config {
        private int minAge;
        private int maxAge;

        public int getMinAge() {
            return minAge;
        }

        public void setMinAge(int minAge) {
            this.minAge = minAge;
        }

        public int getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }
    }
}
