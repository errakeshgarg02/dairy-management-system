package com.rakesh.dairy.service;

import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CustomerCreateRequest;

public interface CustomerService {
	
	public AbstractResponse registerCustomer(CustomerCreateRequest request);

}
