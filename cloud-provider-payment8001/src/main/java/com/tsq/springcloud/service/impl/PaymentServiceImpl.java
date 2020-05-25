package com.tsq.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsq.springcloud.dao.PaymentDao;
import com.tsq.springcloud.entities.Payment;
import com.tsq.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;

	@Override
	@Transactional
	public int save(Payment payment) {
		return paymentDao.save(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}

}
