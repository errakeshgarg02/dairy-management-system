package com.rakesh.dairy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakesh.dairy.entity.Customer;

@Repository
public interface CustomerRespository  extends JpaRepository<Customer, Integer>{

}
