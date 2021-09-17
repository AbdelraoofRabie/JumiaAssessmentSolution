package com.jumia.demo.phonenumbervalidation.model.response;

import java.io.Serializable;

import com.jumia.demo.phonenumbervalidation.dto.BaseDto;

public class DTOSingleResult<ID extends Serializable, T extends BaseDto<ID>> extends BaseResponse
		implements Serializable {

	private static final long serialVersionUID = -6436038853296924496L;
	private T model;

	public DTOSingleResult(boolean errorStatus, String responseMessage, String errorCode, T model) {
		super(errorStatus, responseMessage, errorCode);
		this.model = model;
	}

	public DTOSingleResult(boolean errorStatus, String errorCode, T model) {
		super(errorStatus, errorCode);
		this.model = model;
	}

	public DTOSingleResult(T model) {
		this.model = model;
	}

	public DTOSingleResult() {

	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}
