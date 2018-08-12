package com.rakesh.dairy.model;

import javax.validation.constraints.NotNull;

public class CreateCustomerRequest {
	
	private String customerCode;
	
	@NotNull
	private String name;
	private String fatherName;
	
	@NotNull
	private Long mobileNumber;
	private String dateOfBirth;
	private String address;
	
	
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "CustomerCreateRequest [name=" + name + ", fatherName=" + fatherName + ", mobileNumber=" + mobileNumber
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + "]";
	}
	
	
}
