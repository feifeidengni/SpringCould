package com.feifei.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 *  自定义负载平衡
 * */
public interface LoadBalancer
{
    // 定义接口方法 并返回 ServiceInstance实例
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
