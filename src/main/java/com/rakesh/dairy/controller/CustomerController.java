package com.rakesh.dairy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateCustomerRequest;
import com.rakesh.dairy.model.CustomerData;
import com.rakesh.dairy.model.CustomerUpdateRequest;
import com.rakesh.dairy.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/register")
	public ModelAndView createCustomer(@ModelAttribute("createRequest") CreateCustomerRequest createRequest) throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		AbstractResponse response = customerService.registerCustomer(createRequest);	
		modelAndView.addObject("message", response.getMessage());
		modelAndView.setViewName("redirect:/customer/signup");		
		return modelAndView;
	}

	@GetMapping(value = "/find")
	public ModelAndView findCustomer(@RequestParam("input") String input, @RequestParam("isMultiple") Boolean isMultiple) throws DairyException {
		List<CustomerData> customerData = customerService.searchCustomer(input, isMultiple);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Customers");
		modelAndView.addObject("customers", customerData);
		return modelAndView;
	}

	@PostMapping(value = "/update")
	public ModelAndView updateCustomer(@ModelAttribute("updateCustomer") CustomerUpdateRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/customer/findAll");
		customerService.updateCustomer(request);
		return modelAndView;
	}

	@GetMapping(value = "/delete")
	public AbstractResponse deleteCucstomer(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber) {
		return customerService.deleteCustomer(name, mobileNumber);
	}

	@GetMapping(value = "/findAll")
	public ModelAndView findAllCustomers() {
		AbstractResponse response =  customerService.findAllCustomer();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Customers");
		modelAndView.addObject("customers", response.getData());
		return modelAndView;
	}
	
	@GetMapping("/edit")
	public ModelAndView editCustomer(@RequestParam(value = "id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EditCustomer");
		AbstractResponse response = customerService.findCustomerById(id);
		CustomerUpdateRequest customerUpdateRequest = (CustomerUpdateRequest) response.getData();
		modelAndView.addObject("updateCustomer", customerUpdateRequest);
		return modelAndView;
	}
	
	@GetMapping(value="/searchCustomer")
	public List<CustomerData> searchCustomer(@RequestParam("input") String input, @RequestParam("isMultiple") Boolean isMultiple) throws DairyException {
		 List<CustomerData> customerData = customerService.searchCustomer(input, isMultiple);
		return customerData;
	}

}
