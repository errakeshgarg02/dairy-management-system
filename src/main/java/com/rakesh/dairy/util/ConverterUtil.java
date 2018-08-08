package com.rakesh.dairy.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.entity.Milk;
import com.rakesh.dairy.entity.Rate;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.CreateMilkRequest;
import com.rakesh.dairy.model.CustomerCreateRequest;
import com.rakesh.dairy.model.CustomerUpdateRequest;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.model.UpdateRateRequest;

@Component
public class ConverterUtil {

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

	public Customer convertToCustomerEntity(CustomerCreateRequest createRequest) throws DairyException {

		if (createRequest == null) {
			throw new DairyException("CustomerCreateRequest can not be null");
		}

		Customer customer = new Customer();
		customer.setAddress(createRequest.getAddress());
		customer.setDateOfBirth(stringToLocalDate(createRequest.getDateOfBirth()));
		customer.setFatherName(createRequest.getFatherName());
		customer.setName(createRequest.getName());
		customer.setMobileNumber(createRequest.getMobileNumber());
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

		return customer;
	}

	private LocalDate stringToLocalDate(String date) {
		return LocalDate.parse(date, dateFormatter);
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
	
	public Milk convertToMilkEntity(CreateMilkRequest request) throws DairyException {
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
}
