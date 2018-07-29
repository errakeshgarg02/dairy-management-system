package com.rakesh.dairy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakesh.dairy.entity.Milk;

@Repository
public interface MilkRepository extends JpaRepository<Milk, Integer>{

}