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

	private static final String PAYMENT_URL = "http://consul-payment-service/";

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("consul")
	public String paymentConsul()  {
		return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
	}
}
