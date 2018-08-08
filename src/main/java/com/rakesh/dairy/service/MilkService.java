package com.rakesh.dairy.service;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateMilkRequest;

public interface MilkService {
	
	public AbstractResponse saveMilk(CreateMilkRequest request) throws DairyException;

}
