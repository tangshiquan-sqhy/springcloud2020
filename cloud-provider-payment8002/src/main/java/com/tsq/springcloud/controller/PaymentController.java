package com.tsq.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsq.springcloud.entities.CommonResult;
import com.tsq.springcloud.entities.Payment;
import com.tsq.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@PostMapping("save")
	public CommonResult save(@RequestBody Payment payment) {
		log.info("*****输入参数是：" + payment);
		int result = paymentService.save(payment);
		log.info("*****插入结果:" + result);

		if (result > 0) {
			return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
		} else {
			return new CommonResult(444, "插入数据库失败");
		}
	}

	@GetMapping("get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		log.info("*****输入参数是：" + id);
		Payment payment = paymentService.getPaymentById(id);
		log.info("*****查询结果：" + payment);

		if (payment != null) {
			return new CommonResult<Payment>(200, "查询成功,serverPort:" + serverPort, payment);
		} else {
			return new CommonResult<Payment>(444, "没有对应记录，查询ID：" + id);
		}
	}

	@GetMapping("discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();
		for (String service : services) {
			System.out.println(service);
		}

		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		for (ServiceInstance instance : instances) {
			System.out.println(instance.getServiceId() + " \t " + instance.getHost() + " \t " + instance.getPort()
					+ " \t " + instance.getUri());
		}

		return discoveryClient;
	}
}
