package com.rakesh.dairy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="rate")
@EntityListeners(AuditingEntityListener.class)
public class Rate implements Serializable {
	
	private static final long serialVersionUID = -5394443686093328277L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rate_id", unique = true, nullable = false)
	private Integer id;
	private Float fat;	
	private Float snf;
	private Double rate;
	
	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDate createdDate;
	
    @Column(nullable = false)
    @LastModifiedDate	
	private LocalDate updatedDate;
    
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
	
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Rate [id=" + id + ", fat=" + fat + ", snf=" + snf + ", rate=" + rate + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	

}
