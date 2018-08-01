package com.rakesh.dairy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.rakesh.dairy.constant.MilkType;
import com.rakesh.dairy.constant.Shift;

@Entity
@Table(name="milk")
@EntityListeners(AuditingEntityListener.class)
public class Milk implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "milk_id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	private Float weight;
	private Float fat;
	private Float snf;
	private Double price;
	private MilkType milkType;
	private Shift shift;
	private LocalDate date;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
		return "Milk [id=" + id + ", customer=" + customer + ", weight=" + weight + ", fat=" + fat + ", snf=" + snf
				+ ", price=" + price + ", milkType=" + milkType + ", shift=" + shift + ", date=" + date
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
