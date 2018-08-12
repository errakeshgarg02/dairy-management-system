package com.rakesh.dairy.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.constant.MilkType;
import com.rakesh.dairy.constant.Shift;
import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.entity.Milk;
import com.rakesh.dairy.entity.Rate;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.MilkRequest;
import com.rakesh.dairy.model.CreateCustomerRequest;
import com.rakesh.dairy.model.CustomerData;
import com.rakesh.dairy.model.CustomerUpdateRequest;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.model.UpdateMilkRequest;
import com.rakesh.dairy.model.UpdateRateRequest;

@Component
public class ConverterUtil {

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	

	public Customer convertToCustomerEntity(CreateCustomerRequest createRequest) throws DairyException {

		Customer customer = new Customer();
		customer.setAddress(createRequest.getAddress());
		customer.setDateOfBirth(stringToLocalDate(createRequest.getDateOfBirth()));
		customer.setFatherName(createRequest.getFatherName());
		customer.setName(createRequest.getName());
		customer.setMobileNumber(createRequest.getMobileNumber());
		customer.setCustomerCode(createRequest.getCustomerCode());
		return customer;
	}

	public Customer convertToCustomerEntity(CustomerUpdateRequest updateRequest, Customer customer) {

		if (!StringUtils.isEmpty(updateRequest.getAddress())) {
			customer.setAddress(updateRequest.getAddress());
		}
		if (!StringUtils.isEmpty(updateRequest.getDateOfBirth())) {
			customer.setDateOfBirth(stringToLocalDate(updateRequest.getDateOfBirth()));
		}
		if (!StringUtils.isEmpty(updateRequest.getFatherName())) {
			customer.setFatherName(updateRequest.getFatherName());
		}

		if (!StringUtils.isEmpty(updateRequest.getMobileNumber())) {
			customer.setMobileNumber(updateRequest.getMobileNumber());
		}
		
		if (!StringUtils.isEmpty(updateRequest.getName())) {
			customer.setName(updateRequest.getName());
		}		

		return customer;
	}

	public LocalDate stringToLocalDate(String date) {
		return LocalDate.parse(date, dateFormatter);
	}
	
	public String localDateToString(LocalDate date) {
		return date.toString();
	}
	
	public Rate convertToRateEntity(RateRequest rateRequest) throws DairyException {
		if (rateRequest == null) {
			throw new DairyException("request can not be null");
		}
		Rate rate = new Rate();
		rate.setFat(rateRequest.getFat());
		rate.setRate(rateRequest.getRate());
		rate.setSnf(rateRequest.getSnf());

		return rate;
	}
	
	public Rate convertToRateEntity(UpdateRateRequest rateRequest, Rate rate) throws DairyException {
		
		if(!StringUtils.isEmpty(rateRequest.getSnf())) {
			rate.setSnf(rateRequest.getSnf());
		}
		
		if(!StringUtils.isEmpty(rateRequest.getFat())) {
			rate.setFat(rateRequest.getFat());
		}
		
		if(!StringUtils.isEmpty(rateRequest.getRate())) {
			rate.setRate(rateRequest.getRate());
		}

		return rate;
	}
	
	public Milk convertToMilkEntity(MilkRequest request) throws DairyException {
		if(StringUtils.isEmpty(request)) {
			throw new DairyException("Request cant not be null");
		}
		Milk milk = new Milk();
		milk.setDate(stringToLocalDate(request.getDate()));
		milk.setFat(request.getFat());
		milk.setMilkType(request.getMilkType().getValue());
		milk.setShift(request.getShift().getValue());
		milk.setSnf(request.getSnf());
		milk.setWeight(request.getWeight());
		
		return milk;
	}
	
	
	public Milk convertToMilkEntity(MilkRequest request, Optional<Milk> optionalMilk) throws DairyException {
		
		if(!optionalMilk.isPresent()) {
			throw new DairyException("Milk does not exits");
		}
		
		Milk milk = optionalMilk.get();
		
		if(!StringUtils.isEmpty(request.getDate())) {
			milk.setDate(stringToLocalDate(request.getDate()));
		}
		
		if(!StringUtils.isEmpty(request.getFat())) {
			milk.setFat(request.getFat());
		}
		
		if(!StringUtils.isEmpty(request.getMilkType())) {
			milk.setMilkType(request.getMilkType().getValue());
		}
		
		if(!StringUtils.isEmpty(request.getShift())) {
			milk.setShift(request.getShift().getValue());
		}
		
		if(!StringUtils.isEmpty(request.getSnf())) {
			milk.setSnf(request.getSnf());
		}
		
		if(!StringUtils.isEmpty(request.getWeight())) {
			milk.setWeight(request.getWeight());
		}
		
		return milk;
	}	
	
	public CustomerUpdateRequest convertCustomerToCustomerUpdateRequest(Customer customer) {
		
		CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();
		customerUpdateRequest.setAddress(customer.getAddress());
		customerUpdateRequest.setCustomerCode(customer.getCustomerCode());
		customerUpdateRequest.setDateOfBirth(localDateToString(customer.getDateOfBirth()));
		customerUpdateRequest.setFatherName(customer.getFatherName());
		customerUpdateRequest.setId(customer.getId());
		customerUpdateRequest.setMobileNumber(customer.getMobileNumber());
		customerUpdateRequest.setName(customer.getName());
		return customerUpdateRequest;
	}
	
	public List<MilkRequest> convertToMilkRequests(List<Milk> milkList) {
		return milkList.stream().map(m -> {
			MilkRequest  milk = new MilkRequest();
			return prepareMilkRequestData(m, milk);
		}).collect(Collectors.toList());
	}

	public MilkRequest convertToMilkRequest(Optional<Milk> optionaMilk) throws DairyException {
		if(!optionaMilk.isPresent()) {
			throw new DairyException("Milk does not exits");
		}
		Milk m = optionaMilk.get();
		MilkRequest  milk = new MilkRequest();
		return prepareMilkRequestData(m, milk);
	}
	
	private MilkRequest prepareMilkRequestData(Milk m, MilkRequest milk) {
		milk.setDate(localDateToString(m.getDate()));
		milk.setFat(m.getFat());
		milk.setId(m.getId());
		milk.setMilkType(MilkType.getMilkType(m.getMilkType()));
		milk.setPrice(m.getPrice());
		milk.setShift(Shift.getShift(m.getShift()));
		milk.setSnf(m.getSnf());
		milk.setWeight(m.getWeight());
		milk.setCustomerCode(m.getCustomer().getCustomerCode());
		milk.setCustomerName(m.getCustomer().getName());
		return milk;
	}


	
	public List<CustomerData> convertToCustomerData(List<Customer> customers) {
		List<CustomerData> collect = customers.stream().map(c ->{
			CustomerData customerData = new CustomerData();
			return prepareCustomerData(c, customerData);
		}).collect(Collectors.toList());
		return collect;
	}

	public CustomerData convertToCustomerData(Optional<Customer> optCustomer) {
		CustomerData customerData = new CustomerData();
		if(optCustomer.isPresent()) {
			Customer c = optCustomer.get();			
			customerData = prepareCustomerData(c, customerData);
		}
		return customerData;
	}
	
	private CustomerData prepareCustomerData(Customer c, CustomerData customerData) {
		customerData.setCustomerCode(c.getCustomerCode());
		customerData.setId(c.getId());
		customerData.setName(c.getName());
		customerData.setAddress(c.getAddress());
		customerData.setDateOfBirth(localDateToString(c.getDateOfBirth()));
		customerData.setFatherName(c.getFatherName());
		customerData.setMobileNumber(c.getMobileNumber());
		return customerData;
	}
	

}
