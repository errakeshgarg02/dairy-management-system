package com.rakesh.dairy.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rakesh.dairy.entity.Milk;

@Repository
public interface MilkRepository extends JpaRepository<Milk, Integer>{
	
	@Query("SELECT ch FROM Milk ch where ch.customer.id = :customerId")
	public List<Milk> findMilkByCustomer(Integer customerId);
	
	@Query("SELECT ch FROM Milk ch where ch.customer.id = :customerId AND date BETWEEN :from AND :to")
	public List<Milk> findByCustomerAndDateBetween(Integer customerId, LocalDate from, LocalDate to);
}
