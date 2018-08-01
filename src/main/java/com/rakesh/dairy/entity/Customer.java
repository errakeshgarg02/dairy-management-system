package com.rakesh.dairy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	private Integer id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String customerCode;
	private String fatherName;
	private Long mobileNumber;
	private String address;
	
	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDate createdDate;
	
    @Column(nullable = false)
    @LastModifiedDate	
	private LocalDate updatedDate;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade=CascadeType.ALL)
	private Set<Milk> milks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
	
	

	public Set<Milk> getMilks() {
		return milks;
	}

	public void setMilks(Set<Milk> milks) {
		this.milks = milks;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", customerCode=" + customerCode + ", fatherName=" + fatherName + ", mobileNumber="
				+ mobileNumber + ", address=" + address + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", milks=" + milks + "]";
	}
}
