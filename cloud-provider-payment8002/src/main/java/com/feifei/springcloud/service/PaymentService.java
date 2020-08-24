package com.feifei.springcloud.service;

import com.feifei.springcloud.pojo.Payment;

public interface PaymentService {
    //添加
    public int create(Payment payment);
    //查询
    public Payment getPaymentbyId(Long id);

}
