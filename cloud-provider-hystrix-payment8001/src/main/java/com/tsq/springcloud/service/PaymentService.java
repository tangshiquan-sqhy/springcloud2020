package com.tsq.springcloud.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;

@Service
public class PaymentService {

	public String paymentInfo_OK(Integer id) {
		return "线程池：" + Thread.currentThread().getName() + " \t paymentInfo_OK,id:" + id;
	}

	@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	public String paymentInfo_TimeOut(Integer id) {
		int time = 5;
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池：" + Thread.currentThread().getName() + " \t paymentInfo_TimeOut,id:" + id + "耗时（秒）：" + time;
	}

	public String paymentInfo_TimeOutHandler(Integer id) {
		return "线程池：" + Thread.currentThread().getName() + " \t 系统繁忙，请稍后再试,id:" + id;
	}

	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间范围
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
	})
	public String paymentCircuitBreaker(Integer id) {
		if (id < 0) {
			throw new RuntimeException("*******id不能负数");
		}
		
		String serialNumber = IdUtil.simpleUUID();
		return "*****线程池：" + Thread.currentThread().getName() + " \t 调用成功。流水号：" + serialNumber;
	}
	
	public String paymentCircuitBreaker_fallback(Integer id) {
		return "id不能负数，请稍候再试!!!!id:" + id;
	}
}
