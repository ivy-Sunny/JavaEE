package com.ivy.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    /**
     * IRule 接口
     * AbstractLoadBalancerRule 抽象类
     *
     * AvailabilityFilteringRule : 会先过滤掉：崩溃，访问故障的服务，对剩下服务的进行轮询
     * RoundRobinRule : 轮询
     * RandomRule : 随机
     * RetryRule : 会先按照轮询获取服务～，如果服务获取失败，则会在指定时间内进行重试
     */

    /**
     * 注入到 Spring容器中
     * @LoadBalanced 配置注解均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
