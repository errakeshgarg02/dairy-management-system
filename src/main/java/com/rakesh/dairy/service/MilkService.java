package com.rakesh.dairy.service;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.MilkRequest;
import com.rakesh.dairy.model.SearchMilkRequest;
import com.rakesh.dairy.model.UpdateMilkRequest;

public interface MilkService {
	
	public AbstractResponse saveMilk(MilkRequest request) throws DairyException;
	
	public AbstractResponse updateMilk(MilkRequest request)  throws DairyException;
	
	public AbstractResponse findCustomerMilk(String customerCode, Integer cutomerId) throws DairyException;
	
	public AbstractResponse findMilkById(Integer id) throws DairyException;
	
	public AbstractResponse searchMilkBetweenDates(SearchMilkRequest searchMilkRequest);
	
	public AbstractResponse deleteMilkById(Integer id);
	
	public AbstractResponse deleteMilkByCustomerCode(String customerCode);

}
