package com.tsq.springcloud.service;

import com.tsq.springcloud.entities.Payment;

public interface PaymentService {
	
	public int save(Payment payment);
	
	public Payment getPaymentById(Long id);
}
