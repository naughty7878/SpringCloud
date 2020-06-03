package com.test.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class MyCustomeRule extends AbstractLoadBalancerRule {

    private int total = 0;             // 总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;    // 当前提供服务的下标

    public Server choose(ILoadBalancer loadBalancer, Object key) {

        if (loadBalancer == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = loadBalancer.getReachableServers(); //当前存活的服务
            List<Server> allList = loadBalancer.getAllServers();  //获取全部的服务

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

            //int index = rand.nextInt(serverCount);
            //server = upList.get(index);
            if(total < 5)
            {
                server = upList.get(currentIndex);
                total++;
            }else {
                total = 0;
                currentIndex++;
                if(currentIndex >= upList.size())
                {
                    currentIndex = 0;
                }
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }
        return server;
    }

    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }


    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
