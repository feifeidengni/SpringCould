package com.feifei.springcloud.service;

import com.feifei.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    //添加
    public int create(Payment payment);
    //查询
    public Payment getPaymentbyId( Long id);

}
