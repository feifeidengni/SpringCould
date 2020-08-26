package com.feifei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatWayMain9527
{
    public static void main(String[] args) {
        SpringApplication.run(GatWayMain9527.class,args);
    }
}
