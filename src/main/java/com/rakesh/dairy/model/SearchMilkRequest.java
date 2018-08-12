package com.rakesh.dairy.model;

import java.io.Serializable;

public class SearchMilkRequest implements Serializable {
	
	private static final long serialVersionUID = 2673562915759946201L;
	private Integer customerId;
	private String customerCode;
	private String customerName;
	private String fromDate;
	private String toDate;
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "SearchMilkRequest [customerId=" + customerId + ", customerCode=" + customerCode + ", customerName="
				+ customerName + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	

}
