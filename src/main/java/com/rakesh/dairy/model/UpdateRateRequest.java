package com.rakesh.dairy.model;

import java.io.Serializable;

public class UpdateRateRequest implements Serializable {
	
	private static final long serialVersionUID = -2518012717254178487L;
	private Integer id;
	private Float fat;
	private Float snf;
	private Double rate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getFat() {
		return fat;
	}
	public void setFat(Float fat) {
		this.fat = fat;
	}
	public Float getSnf() {
		return snf;
	}
	public void setSnf(Float snf) {
		this.snf = snf;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "RateCreateRequest [fat=" + fat + ", snf=" + snf + ", rate=" + rate + "]";
	}
	
}
