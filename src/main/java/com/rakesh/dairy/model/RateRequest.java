package com.rakesh.dairy.model;

import java.io.Serializable;

public class RateRequest implements Serializable {
	
	private static final long serialVersionUID = -2997011363677141392L;
	private Float fat;
	private Float snf;
	private Double rate;
	private Integer id;
	
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "RateRequest [fat=" + fat + ", snf=" + snf + ", rate=" + rate + ", id=" + id + "]";
	}
	
}
