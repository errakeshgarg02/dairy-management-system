package com.rakesh.dairy.util;

import org.springframework.stereotype.Component;

import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.model.CustomerCreateRequest;

@Component
public class ConverterUtil {

	public Customer convertToCustomerEntity(CustomerCreateRequest createRequest) {

		if (createRequest == null) {
			return null;
		}

		Customer customer = new Customer();
		customer.setAddress(createRequest.getAddress());
		customer.setDateOfBirth(createRequest.getDateOfBirth());
		customer.setFatherName(createRequest.getFatherName());
		customer.setFirstName(createRequest.getFirstName());
		customer.setLastName(createRequest.getLastName());
		customer.setMobileNumber(createRequest.getMobileNumber());

		return customer;
	}
}
