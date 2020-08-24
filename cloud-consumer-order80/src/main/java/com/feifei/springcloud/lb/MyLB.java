package com.feifei.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class MyLB implements  LoadBalancer
{
    // 给定初始值
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    // 坐标
    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get(); //获取当前的初始值
            // 2147483647 : Integer 的最大整数值， 每访问一次 数值就加 1
            next = current >= 2147483647 ? 0 :current + 1;
        }while( !this.atomicInteger.compareAndSet(current,next) );
        System.out.println("****第几次访问，次数next: " + next);
        return next;
    }

    /**
     *  List<ServiceInstance> serviceInstances : 获取机器的列表，微服务的集合
     * */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        //获取服务器的下标
        /**
         *  负载均衡算法： rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务器重启后rest接口会从1开始。
         * */
        int index =  getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);     //通过下标获取单个微服务
    }
}
