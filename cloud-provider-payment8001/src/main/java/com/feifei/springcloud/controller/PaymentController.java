package com.feifei.springcloud.controller;

import com.feifei.springcloud.pojo.CommonResult;
import com.feifei.springcloud.pojo.Payment;
import com.feifei.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j      //打印日志
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String servicePort;
    //通过服务发现来获得该服务的信息
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        Integer result =  paymentService.create(payment);
        log.info("****插入结果：** " + result);
        if(result > 0 ){
            return new CommonResult(200,"插入数据库成功,serverPort:" + servicePort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment =  paymentService.getPaymentbyId(id);
        log.info("****插入结果：** " + payment+"\t 很爱很爱你" );
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort:" + servicePort,payment);
        }else{
            return new CommonResult(444,"没有对应的记录，查询的id是：" + id,null);
        }
    }

    /**
     *  通过服务发现来获得该服务的信息
     * */
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        // 获取微服务名称的集合
        List<String> services = this.discoveryClient.getServices();
        for (String service: services) {
            //获取微服务名称
            log.info("**********element:" + service);
            // 循环获取每个微服务的网络信息。
           /*
            List<ServiceInstance> instances = this.discoveryClient.getInstances(service);
            for (ServiceInstance instance: instances ) {
                log.info("服务id:" + instance.getServiceId() + "\n\t 地址：" +instance.getHost() + "\n\t 端口号" +
                        "：" + instance.getPort() + "\n\t 网络地址：" + instance.getUri() );
            }
            */
        }
        //  获取单个微服务的网络信息
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances ) {
            log.info("服务id:" + instance.getServiceId() + "\n\t 地址：" +instance.getHost() + "\n\t 端口号" +
                    "：" + instance.getPort() + "\n\t 网络ip地址：" + instance.getUri() );
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return this.servicePort;
    }
    /**
     *  自定义一个睡眠等待时间
     * */
    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return servicePort;
    }








}
