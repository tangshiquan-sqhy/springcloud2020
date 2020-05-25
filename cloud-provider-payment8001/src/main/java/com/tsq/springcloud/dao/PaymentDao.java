package com.tsq.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsq.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {
	
	public int save(Payment payment);
	
	public Payment getPaymentById(@Param("id") Long id);
}
