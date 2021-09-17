package com.jumia.demo.phonenumbervalidation.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class BaseDto<ID extends Serializable> implements Serializable{

	private static final long serialVersionUID = -6188502829143318663L;

	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	
}
