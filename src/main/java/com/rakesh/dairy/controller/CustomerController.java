package com.rakesh.dairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CustomerCreateRequest;
import com.rakesh.dairy.model.CustomerUpdateRequest;
import com.rakesh.dairy.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse createCustomer(@RequestBody CustomerCreateRequest createRequest) throws DairyException {
		return customerService.registerCustomer(createRequest);
	}

	@GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse findCustomer(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "mobileNumber", required = false) Long mobileNumber) {
		return customerService.findCustomer(name, mobileNumber);
	}

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse updateCustomer(@RequestBody CustomerUpdateRequest request) {
		return customerService.updateCustomer(request);
	}

	@GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse deleteCucstomer(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber) {
		return customerService.deleteCustomer(name, mobileNumber);
	}

	@GetMapping(value = "/findAll")
	public AbstractResponse findAllCustomers() {
		return customerService.findAllCustomer();
	}

}
