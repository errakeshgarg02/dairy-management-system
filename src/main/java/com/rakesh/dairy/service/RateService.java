package com.rakesh.dairy.service;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.model.UpdateRateRequest;

public interface RateService {
	
	public AbstractResponse createRate(RateRequest rateRequest) throws DairyException;
	
	public AbstractResponse updateRate(UpdateRateRequest rateRequest) throws DairyException;
	
	public AbstractResponse findAll() throws DairyException;
	
	public AbstractResponse deleteById(Integer id) throws DairyException;
	
	public AbstractResponse findById(Integer id) throws DairyException;
	
	public AbstractResponse truncate() throws DairyException;	

}
