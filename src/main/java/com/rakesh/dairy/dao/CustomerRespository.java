package com.rakesh.dairy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rakesh.dairy.entity.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByMobileNumber(Long mobileNumber);

	@Query("SELECT max(ch.customerCode) FROM Customer ch")
	public String getMaxCustomerCode();

	@Query("SELECT ch FROM Customer ch where ch.name like %:name% and ch.mobileNumber= :mobileNumber")
	public List<Customer> findByNameAndMobileNumber(String name, Long mobileNumber);

	@Query("SELECT ch FROM Customer ch where ch.name like %:name%")
	public List<Customer> findByName(String name);
	
	public Optional<Customer> findByCustomerCode(String customerCode);

}
