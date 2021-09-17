package com.jumia.demo.phonenumbervalidation.dto;

public class CustomerDto extends BaseDto<Integer> {

	private static final long serialVersionUID = -8685744139986114250L;
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
