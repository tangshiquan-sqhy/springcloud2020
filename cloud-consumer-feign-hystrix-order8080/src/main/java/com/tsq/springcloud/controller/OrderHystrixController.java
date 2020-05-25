package com.tsq.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tsq.springcloud.service.PaymentHystrixService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("order")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

	@Autowired
	private PaymentHystrixService paymentHystrixService;
	
	@Value("${server.port}")
	private String serverPort;
	
	@GetMapping("hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		String result = paymentHystrixService.paymentInfo_OK(id);
		log.info("******result:" + result);
		return result;
	}
	
	@GetMapping("hystrix/timeout/{id}")
	@HystrixCommand
	public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
	//	int a = 10/0;
		String result = paymentHystrixService.paymentInfo_TimeOut(id);
		log.info("******result:" + result);
		return result;
	}
	
	public String payment_Global_FallbackMethod() {
		return "Global****异常处理信息，请稍后再试!!";
	}
}
