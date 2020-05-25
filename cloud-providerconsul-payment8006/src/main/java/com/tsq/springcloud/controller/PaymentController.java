package com.tsq.springcloud.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

	@Value("${server.port}")
	private String serverPort;

	@GetMapping("consul")
	public String paymentConsul() {
		return "springcloud with consul:" + serverPort + "\t" + UUID.randomUUID().toString();
	}
}
