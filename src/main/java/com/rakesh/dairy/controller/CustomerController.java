package com.rakesh.dairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CustomerCreateRequest;
import com.rakesh.dairy.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value = "/register", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse createCustomer(@RequestBody CustomerCreateRequest createRequest) {

		return customerService.registerCustomer(createRequest);
	}

}
