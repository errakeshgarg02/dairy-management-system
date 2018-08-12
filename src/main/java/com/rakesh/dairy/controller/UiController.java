package com.rakesh.dairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateCustomerRequest;
import com.rakesh.dairy.model.MilkRequest;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.model.SearchMilkRequest;
import com.rakesh.dairy.model.CustomerUpdateRequest;
import com.rakesh.dairy.service.CustomerService;

@RestController
public class UiController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/signup")
	public ModelAndView signupPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("CustomerRegistration");
		CreateCustomerRequest createRequest = new CreateCustomerRequest();
		createRequest.setCustomerCode(customerService.getMaxConsumerCode().getData().toString());
		modelAndView.addObject("createRequest", createRequest);
		return modelAndView;
	}

	@GetMapping("/customer/search")
	public ModelAndView searchCustomerPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("SearchCustomer");
		return modelAndView;
	}

	@GetMapping("/milk/addMilkPageWithCustomer")
	public ModelAndView addMilkPage(@RequestParam("customerId") Integer customerId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("AddMilkWithCustomerId");
		AbstractResponse findCustomerById = customerService.findCustomerById(customerId);
		CustomerUpdateRequest customerUpdateRequest = (CustomerUpdateRequest) findCustomerById.getData();
		MilkRequest createMilkRequest = new MilkRequest();
		createMilkRequest.setCustomerCode(customerUpdateRequest.getCustomerCode());
		createMilkRequest.setCustomerName(customerUpdateRequest.getName());
		modelAndView.addObject("createMilkRequest", createMilkRequest);
		return modelAndView;
	}

	@GetMapping("/milk/searchMilkPage")
	public ModelAndView searchMilkPage() {
		ModelAndView modelAndView = new ModelAndView("SearchMilk", "searchMilkRequest", new SearchMilkRequest());
		return modelAndView;
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/milk/addMilkPage")
	public ModelAndView addMilkPage() {
		ModelAndView modelAndView = new ModelAndView("AddMilk", "createMilkRequest", new MilkRequest());
		return modelAndView;
	}
	
	@GetMapping("/rate/addRatePage")
	public ModelAndView addRatePage() {
		ModelAndView modelAndView = new ModelAndView("AddRate", "createRate", new RateRequest());		
		return modelAndView;
	}
}
