package com.tsq.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsq.springcloud.entities.CommonResult;
import com.tsq.springcloud.entities.Payment;
import com.tsq.springcloud.service.PaymentFeignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("order")
public class OrderFeignController {

	@Autowired
	private PaymentFeignService paymentFeignService;

	@PostMapping("save")
	public CommonResult save(Payment payment) {
		log.info("*****输入参数是：" + payment);
		CommonResult result = paymentFeignService.save(payment);
		log.info("*****插入结果:" + result);
		return result;
	}

	@GetMapping("get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		log.info("*****输入参数是：" + id);
		CommonResult<Payment> result = paymentFeignService.getPaymentById(id);
		log.info("*****查询结果：" + result);
		return result;
	}
}
