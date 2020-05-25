package com.tsq.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tsq.springcloud.entities.CommonResult;
import com.tsq.springcloud.entities.Payment;

@RequestMapping("payment")
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
	
	@PostMapping("save")
	public CommonResult save(@RequestBody Payment payment);
	
	@GetMapping("get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
