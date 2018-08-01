package com.rakesh.dairy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rate")
public class Rate implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rate_id", unique = true, nullable = false)
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
		return "Rate [id=" + id + ", fat=" + fat + ", snf=" + snf + ", rate=" + rate + "]";
	}
	

}
