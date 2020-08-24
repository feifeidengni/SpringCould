package com.feifei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient   //注册Eureka 服务中心
@EnableCircuitBreaker
public class PaymentHysrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHysrixMain8001.class,args);
    }
}
