package com.feifei.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient   //注册Eureka 服务中心
@EnableCircuitBreaker  //对hystrix熔断机制的支持
public class PaymentHysrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHysrixMain8001.class,args);
    }

    /**
     *  此配置是为了服务端而配置，与服务容错本身无关，springcloud升级后的坑，
     *  servletRegistrationBean因为springboot的默认路径不是 "/hystrix.stream",
     *  只要在自己项目里配置上下面的servlet就可以了。
     * */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
