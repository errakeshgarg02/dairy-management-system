package com.rakesh.dairy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakesh.dairy.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
	
	public Optional<Rate> findRateByFatAndSnf(Float fat, Float snf);

}
