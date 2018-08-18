package com.rakesh.dairy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.dao.CustomerRespository;
import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateCustomerRequest;
import com.rakesh.dairy.model.CustomerData;
import com.rakesh.dairy.model.CustomerUpdateRequest;
import com.rakesh.dairy.util.ConverterUtil;
import com.rakesh.dairy.util.DairyUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRespository;
	@Autowired
	private ConverterUtil converterUtil;
	
	@Autowired
	private DairyUtil dairyUtil;

	@Override
	public AbstractResponse registerCustomer(CreateCustomerRequest request) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		Optional<Customer> findByMobileNumber = customerRespository.findByMobileNumber(request.getMobileNumber());
		if (findByMobileNumber.isPresent()) {
			throw new DairyException("Customer already exits");
		}
		Customer customer = converterUtil.convertToCustomerEntity(request);
		customer = customerRespository.save(customer);
		response.setData(customer.getId());
		response.setMessage("Customer registered! | Customer code is :"+customer.getCustomerCode());
		return response;
	}

	@Override
	public AbstractResponse updateCustomer(CustomerUpdateRequest request) {
		AbstractResponse response = new AbstractResponse();
		Optional<Customer> findById = customerRespository.findById(request.getId());
		if (findById.isPresent()) {
			Customer customer = converterUtil.convertToCustomerEntity(request, findById.get());
			customer = customerRespository.save(customer);
			response.setData(customer.getId());
		} else {
			response.setStatus(404);
			response.setData("Customer not found");
		}

		return response;
	}

	@Override
	public AbstractResponse deleteCustomer(String customerCode) {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		Optional<Customer> optionalCustomer = customerRespository.findByCustomerCode(customerCode);
		if (optionalCustomer.isPresent()) {
			customerRespository.delete(optionalCustomer.get());
			response.setData("Customer deleted");
		}

		return response;
	}

	@Override
	public AbstractResponse findCustomer(String name, Long mobileNumber) {
		AbstractResponse response = new AbstractResponse();
		if (StringUtils.isEmpty(name) && StringUtils.isEmpty(mobileNumber)) {
			response.setStatus(400);
			response.setData("Please enter name or mobile number");
		}
		if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(mobileNumber)) {
			List<Customer> findByNameAndMobileNumber = customerRespository.findByNameAndMobileNumber(name,
					mobileNumber);
			response.setData(findByNameAndMobileNumber);
		}
		if (!StringUtils.isEmpty(name) && StringUtils.isEmpty(mobileNumber)) {
			response.setData(customerRespository.findByName(name));
		}
		if (StringUtils.isEmpty(name) && !StringUtils.isEmpty(mobileNumber)) {
			List<Customer> list = new ArrayList<>();
			Optional<Customer> findByMobileNumber = customerRespository.findByMobileNumber(mobileNumber);
			if(findByMobileNumber.isPresent()) {
				list.add(findByMobileNumber.get());
			}			
			response.setData(list);
		}

		return response;
	}

	@Override
	public AbstractResponse findAllCustomer() {
		List<Customer> findAll = customerRespository.findAll();
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		response.setData(findAll);
		return response;
	}

	@Override
	public AbstractResponse getMaxConsumerCode() {
		AbstractResponse response = new AbstractResponse();
		String customerCode = dairyUtil.increment(customerRespository.getMaxCustomerCode());
		response.setData(customerCode);
		return response;
	}

	@Override
	public AbstractResponse findCustomerById(Integer id) {
		AbstractResponse response = new AbstractResponse();
		Optional<Customer> findById = customerRespository.findById(id);
		if(findById.isPresent()) {
			CustomerUpdateRequest convertCustomerToCustomerUpdateRequest = converterUtil.convertCustomerToCustomerUpdateRequest(findById.get());
			response.setData(convertCustomerToCustomerUpdateRequest);
		}		
		return response;
	}

	@Override
	public List<CustomerData> searchCustomer(String input, Boolean isMultiple) throws DairyException {
		if(StringUtils.isEmpty(input)) {
			throw new DairyException("input data is null");
		}
		List<CustomerData> customerDatas = new ArrayList<>();
		Optional<Customer> optCustomer = Optional.empty();
		
		if(dairyUtil.isCustomerName(input)) {
			List<Customer> customers = customerRespository.findByName(input);
			
			if(customers == null || customers.size() ==0) {
				throw new DairyException("Customer data does not present!");
			}
			List<CustomerData> collect = converterUtil.convertToCustomerData(customers);
			if(isMultiple) {
				customerDatas.addAll(collect);
			} else {
				customerDatas.add(collect.get(0));
			}
		} else if(dairyUtil.isCustomerCode(input)) {
			optCustomer = customerRespository.findByCustomerCode(input);
			customerDatas.add(converterUtil.convertToCustomerData(optCustomer));
			
		} else if(dairyUtil.isMobileNumber(input)) {
			optCustomer = customerRespository.findByMobileNumber(Long.valueOf(input));
			customerDatas.add(converterUtil.convertToCustomerData(optCustomer));
		}
			
		return customerDatas;
	}

}
