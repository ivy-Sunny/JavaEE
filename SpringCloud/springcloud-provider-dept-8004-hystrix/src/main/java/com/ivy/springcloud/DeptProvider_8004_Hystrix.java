package com.ivy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//服务发现
@EnableDiscoveryClient
public class DeptProvider_8004_Hystrix {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8004_Hystrix.class, args);
    }
}
