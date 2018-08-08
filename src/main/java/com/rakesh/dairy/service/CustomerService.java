package com.rakesh.dairy.service;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CustomerCreateRequest;
import com.rakesh.dairy.model.CustomerUpdateRequest;

public interface CustomerService {
	
	public AbstractResponse registerCustomer(CustomerCreateRequest request) throws DairyException;
	
	public AbstractResponse updateCustomer(CustomerUpdateRequest request);
	
	public AbstractResponse deleteCustomer(String name, Long mobileNumber);
	
	public AbstractResponse findCustomer(String name, Long mobileNumber);
	
	public AbstractResponse findAllCustomer();

}
