package com.feifei.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "---paymentInfo_OK-------- fall back 服务端出现故障~ /(ㄒoㄒ)/~~ ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----paymentInfo_TimeOut------- fall back 服务端出现故障~ /(ㄒoㄒ)/~~ ";
    }
}
