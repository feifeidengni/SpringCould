package com.feifei.springcloud.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     *  正常访问的方法
     * */
    public String paymentInfo_OK(Integer id){
        return "线程池 ： " +Thread.currentThread().getName() +"   paymentInfo_OK,   id"+id +"\t O(∩_∩)O";
    }

    /**
     *  配置服务降级处理   @HystrixCommand
     *      该方法出现异常情况 会使用服务降级处理，
     *          fallbackMethod ： 服务降级所执行的方法（兜底方法）
     * */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties ={
            //3秒钟以内就是正常的业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    } )
    public String paymentInfo_TimeOut(Integer id){
        Integer timeout = 5;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //int i = 5/0;
        return "线程池 ： " +Thread.currentThread().getName() +"   paymentInfo_TimeOut,   id"+id +"\t O(∩_∩)O 耗时(m)"+timeout;
    }
    //兜底方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池 ： " +Thread.currentThread().getName() +"   系统报错，或者运行异常,   id"+id +"\t /(ㄒoㄒ)/~~";
    }

}
