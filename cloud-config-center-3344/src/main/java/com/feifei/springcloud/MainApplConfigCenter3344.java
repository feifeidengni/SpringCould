package com.feifei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MainApplConfigCenter3344
{
    public static void main(String[] args) {
        SpringApplication.run(MainApplConfigCenter3344.class,args);
    }
}
