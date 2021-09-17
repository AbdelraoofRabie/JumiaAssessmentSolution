package com.jumia.demo.phonenumbervalidation.model.response;

import java.io.Serializable;

public class CustomResponse<T extends Object> extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 2093778774969805269L;

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
