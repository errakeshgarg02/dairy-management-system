package com.rakesh.dairy.model;

import java.io.Serializable;

public class CustomerUpdateRequest implements Serializable {
	
	private static final long serialVersionUID = -6350181391841281913L;
	private Integer id;
	private String fatherName;
	private Long mobileNumber;
	private String dateOfBirth;
	private String address;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return "CustomerUpdateRequest [id=" + id + ", fatherName=" + fatherName + ", mobileNumber=" + mobileNumber
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + "]";
	}
	
	
}
