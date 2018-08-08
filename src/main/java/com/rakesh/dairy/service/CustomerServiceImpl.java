package com.rakesh.dairy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.dao.CustomerRespository;
import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CustomerCreateRequest;
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
	public AbstractResponse registerCustomer(CustomerCreateRequest request) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		Customer fetchCustomer = customerRespository.findByMobileNumber(request.getMobileNumber());
		if (!StringUtils.isEmpty(fetchCustomer)) {
			response.setStatus(409);
			response.setData("Customer already exits with mobile number");
			return response;
		}
		Customer customer = converterUtil.convertToCustomerEntity(request);
		String maxCustomerCode = customerRespository.getMaxCustomerCode();
		customer.setCustomerCode(dairyUtil.increment(maxCustomerCode));

		Customer save = customerRespository.save(customer);
		response.setData(save.getId());
		return response;
	}

	@Override
	public AbstractResponse updateCustomer(CustomerUpdateRequest request) {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		Optional<Customer> findById = customerRespository.findById(request.getId());
		if (findById.isPresent()) {
			Customer customer = findById.get();
			customer = converterUtil.convertToCustomerEntity(request, customer);
			Customer save = customerRespository.save(customer);
			response.setData(save.getId());
		} else {
			response.setStatus(404);
			response.setData("Customer not found");
		}

		return response;
	}

	@Override
	public AbstractResponse deleteCustomer(String name, Long mobileNumber) {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
		List<Customer> findByNameAndMobileNumber = customerRespository.findByNameAndMobileNumber(name, mobileNumber);
		if (!StringUtils.isEmpty(findByNameAndMobileNumber) && findByNameAndMobileNumber.size() > 0) {
			customerRespository.delete(findByNameAndMobileNumber.get(0));
			response.setData("Customer deleted");
		}

		return response;
	}

	@Override
	public AbstractResponse findCustomer(String name, Long mobileNumber) {
		AbstractResponse response = new AbstractResponse();
		response.setStatus(200);
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
			response.setData(customerRespository.findByMobileNumber(mobileNumber));
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

}
