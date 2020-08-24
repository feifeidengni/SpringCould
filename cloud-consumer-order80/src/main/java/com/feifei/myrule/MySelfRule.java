package com.feifei.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *      定义 Ribbon 特定算法机制访问服务
 *          该类定义为随机的
 * */
@Configuration
public class MySelfRule
{
    @Bean
    public IRule myRule(){
        return new RandomRule();    //定义为随机
    }
}
