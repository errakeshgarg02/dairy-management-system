package com.rakesh.dairy.model;

import java.io.Serializable;

import com.rakesh.dairy.constant.MilkType;
import com.rakesh.dairy.constant.Shift;

public class UpdateMilkRequest implements Serializable {
	
	private static final long serialVersionUID = 2266161955601958117L;
	private Integer id;
	private Float weight;
	private Float fat;
	private Float snf;
	private MilkType milkType;
	private Shift shift;
	private String date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
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
	public MilkType getMilkType() {
		return milkType;
	}
	public void setMilkType(MilkType milkType) {
		this.milkType = milkType;
	}
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
}
