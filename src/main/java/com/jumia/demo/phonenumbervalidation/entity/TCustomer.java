package com.jumia.demo.phonenumbervalidation.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer")
public class TCustomer extends BaseEntity<Integer> {


	private static final long serialVersionUID = 5740514008302534901L;

	private String name;
	private String phone;
	private int valid;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
}
