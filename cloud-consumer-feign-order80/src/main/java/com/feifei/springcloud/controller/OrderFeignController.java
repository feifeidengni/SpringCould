package com.feifei.springcloud.controller;

import com.feifei.springcloud.pojo.CommonResult;
import com.feifei.springcloud.pojo.Payment;
import com.feifei.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController
{
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return this.paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value="/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        log.info("***************:测试超时时间方法");
        //openfeign-ribbon 客户端默认等待 1 秒 ，1秒访问不到数据就出现异常。
        return paymentFeignService.paymentFeignTimeout();
    }
}
