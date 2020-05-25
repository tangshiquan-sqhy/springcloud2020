package com.tsq.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tsq.springcloud.entities.CommonResult;
import com.tsq.springcloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("order")
public class OrderController {

//	private static final String PAYMENT_URL = "http://127.0.0.1:8001/";
	private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/";

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("save")
	public CommonResult save(Payment payment) {
		log.info("*****输入参数是：" + payment);
		CommonResult result = restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, CommonResult.class);
		log.info("*****插入结果:" + result);
		return result;
	}

	@GetMapping("get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		log.info("*****输入参数是：" + id);
		CommonResult<Payment> result = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,
				CommonResult.class);
		log.info("*****查询结果：" + result);
		return result;
	}
}
