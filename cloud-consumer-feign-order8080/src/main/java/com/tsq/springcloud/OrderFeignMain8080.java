package com.tsq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain8080 {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(OrderFeignMain8080.class, args);
	}
}
