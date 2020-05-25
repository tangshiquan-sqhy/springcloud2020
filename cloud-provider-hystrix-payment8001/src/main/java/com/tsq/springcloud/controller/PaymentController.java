package com.tsq.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsq.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Value("${server.port}")
	private String serverPort;
	
	@GetMapping("hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		String result = paymentService.paymentInfo_OK(id);
		log.info("******result:" + result);
		return result;
	}
	
	@GetMapping("hystrix/timeout/{id}")
	public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
		String result = paymentService.paymentInfo_TimeOut(id);
		log.info("******result:" + result);
		return result;
	}
	
	// 服务熔断
	@GetMapping("hystrix/circuit/{id}")
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		String result = paymentService.paymentCircuitBreaker(id);
		log.info("******result:" + result);
		return result;
	}
}
