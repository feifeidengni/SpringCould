package com.feifei.springcloud.service;

import com.feifei.springcloud.pojo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *  @FeignClient(value = "CLOUD-PAYMENT-SERVICE") ： 设置要调用的哪个微服务
 * */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService
{
    /**
     *  要和 controller中的方法一致，包括访问路径
     *      @GetMapping(value = "/payment/get/{id}") : 访问路径一定要和需要访问的路径一致不然报错
     *          报错信息： feign.FeignException$NotFound: status 404 reading PaymentFeignService#getPaymentById(Long)
     * */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    /**
     *  openfeign-ribbon 客户端默认等待 1 秒 ，1秒访问不到数据就出现异常。
     *  Read timed out executing GET http://CLOUD-PAYMENT-SERVICE/payment/feign/timeout
     * feign.RetryableException: Read timed out executing GET http://CLOUD-PAYMENT-SERVICE/payment/feign/timeout
     *
     * */
    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout();
}
