package com.feifei.springcloud.controller;

import com.feifei.springcloud.lb.LoadBalancer;
import com.feifei.springcloud.pojo.CommonResult;
import com.feifei.springcloud.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    //定义一个常量 url
   // public static final String PAYMENT_URL = "http://localhost:8001";
    //  CLOUD-PAYMENT-SERVICE： 微服务的名称
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    //引入自定义的负载均衡类
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     *      postForObject ： 返回对象为响应体中数据转换成的对象，基本上可以理解为 json
     * */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //post请求访问 其他路径
       return restTemplate.postForObject(PAYMENT_URL+"/payment/create" ,payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return this.restTemplate.getForObject(PAYMENT_URL+"/payment/get/" + id,CommonResult.class);
    }

    /**
     *    getForEntity : 返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头，响应状态码，响应体 等。
     * */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/" + id,CommonResult.class);
        log.info(entity.getHeaders()+"\t头部信息");
        log.info(entity.getStatusCode().is2xxSuccessful()+"\t300以内的状态码");
        log.info(entity.getStatusCode().isError()+"\t error报错");
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult(444,"操作失败" );
        }
    }

    @GetMapping("/comsumer/payment/lb")
    public String getPaymentLB(){
        //通过 暴露再外的微服务名称 获取 微服务集合
        List<ServiceInstance> instances =  this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() == 0){
            return null;
        }
        //  获取单个微服务的实例
        ServiceInstance serviceInstance =  this.loadBalancer.instance(instances);
        URI uri =  serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
