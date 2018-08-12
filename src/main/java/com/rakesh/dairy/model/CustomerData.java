package com.rakesh.dairy.model;

import java.io.Serializable;

public class CustomerData implements Serializable {
	
	private static final long serialVersionUID = -891635840552265458L;
	private Integer id;
	private String name;
	private String customerCode;
	private String dateOfBirth;
	private String fatherName;
	private Long mobileNumber;
	private String address;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "CustomerData [id=" + id + ", name=" + name + ", customerCode=" + customerCode + ", dateOfBirth="
				+ dateOfBirth + ", fatherName=" + fatherName + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ "]";
	}


}
