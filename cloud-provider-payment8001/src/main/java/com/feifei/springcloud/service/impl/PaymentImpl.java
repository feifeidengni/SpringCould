package com.feifei.springcloud.service.impl;

import com.feifei.springcloud.dao.PaymentDao;
import com.feifei.springcloud.pojo.Payment;
import com.feifei.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    //查询
    public Payment getPaymentbyId(Long id){
        return paymentDao.getPaymentbyId(id);
    }

}
