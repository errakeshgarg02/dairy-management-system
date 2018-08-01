package com.rakesh.dairy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.dairy.dao.CustomerRespository;
import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CustomerCreateRequest;
import com.rakesh.dairy.util.ConverterUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRespository;
	private ConverterUtil converterUtil;
	
	@Override
	public AbstractResponse registerCustomer(CustomerCreateRequest request) {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		Customer customer = converterUtil.convertToCustomerEntity(request);
		customer.setCustomerCode("A1");
		Customer save = customerRespository.save(customer);
		response.setData(save.getId());
		return response;
	}

	
}
