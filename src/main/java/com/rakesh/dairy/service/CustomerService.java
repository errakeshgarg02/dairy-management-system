package com.rakesh.dairy.service;

import java.util.List;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateCustomerRequest;
import com.rakesh.dairy.model.CustomerData;
import com.rakesh.dairy.model.CustomerUpdateRequest;

public interface CustomerService {
	
	public AbstractResponse registerCustomer(CreateCustomerRequest request) throws DairyException;
	
	public AbstractResponse updateCustomer(CustomerUpdateRequest request);
	
	public AbstractResponse deleteCustomer(String name, Long mobileNumber);
	
	public AbstractResponse findCustomer(String name, Long mobileNumber);
	
	public AbstractResponse findAllCustomer();
	
	public AbstractResponse getMaxConsumerCode();
	
	public AbstractResponse findCustomerById(Integer id);
	
	public List<CustomerData> searchCustomer(String input, Boolean isMultiple) throws DairyException;

}
