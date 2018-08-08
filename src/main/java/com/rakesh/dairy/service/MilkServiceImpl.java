package com.rakesh.dairy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.dairy.dao.CustomerRespository;
import com.rakesh.dairy.dao.MilkRepository;
import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.entity.Milk;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateMilkRequest;
import com.rakesh.dairy.util.ConverterUtil;
import com.rakesh.dairy.util.DairyUtil;

@Service
public class MilkServiceImpl implements MilkService {

	@Autowired
	private MilkRepository milkRepository;
	
	@Autowired
	private CustomerRespository customerRespository;
	
	@Autowired
	private ConverterUtil converterUtil;
	
	@Autowired
	private DairyUtil dairyUtil;
	
	@Override
	public AbstractResponse saveMilk(CreateMilkRequest request) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		Milk milk = converterUtil.convertToMilkEntity(request);
		Optional<Customer> findByCustomerCode = customerRespository.findByCustomerCode(request.getCustomerCode());
		if(!findByCustomerCode.isPresent()) {
			throw new DairyException("Customer does not exist");
		}
		milk.setCustomer(findByCustomerCode.get());
		milk.setPrice(dairyUtil.getPrice(request.getWeight(), request.getFat(), request.getSnf()));
		
		response.setData(milkRepository.save(milk).getId());
		return response;
	}
}
