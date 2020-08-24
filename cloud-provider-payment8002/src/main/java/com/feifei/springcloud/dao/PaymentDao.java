package com.feifei.springcloud.dao;

import com.feifei.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    //添加
    public int create(Payment payment);
    //查询
    public Payment getPaymentbyId(@Param("id") Long id);

}
