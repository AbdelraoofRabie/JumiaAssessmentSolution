package com.jumia.demo.phonenumbervalidation.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {


	private static final long serialVersionUID = 6338339081799110164L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}

	
	
	
}
