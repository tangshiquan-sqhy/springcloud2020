package com.tsq.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
	private Integer code;
	
	private String message;
	
	private T date;
	
	public CommonResult(Integer code, String message) {
		this(code, message, null);
	}
	
}
