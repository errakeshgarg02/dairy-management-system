package com.rakesh.dairy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.dao.CustomerRespository;
import com.rakesh.dairy.dao.MilkRepository;
import com.rakesh.dairy.entity.Customer;
import com.rakesh.dairy.entity.Milk;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.MilkRequest;
import com.rakesh.dairy.model.SearchMilkRequest;
import com.rakesh.dairy.model.UpdateMilkRequest;
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
	public AbstractResponse saveMilk(MilkRequest request) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		Milk milk = converterUtil.convertToMilkEntity(request);
		Optional<Customer> findByCustomerCode = customerRespository.findByCustomerCode(request.getCustomerCode());
		if (!findByCustomerCode.isPresent()) {
			throw new DairyException("Customer does not exist");
		}
		milk.setCustomer(findByCustomerCode.get());
		milk.setPrice(dairyUtil.getPrice(request.getWeight(), request.getFat(), request.getSnf()));

		response.setData(milkRepository.save(milk).getId());
		return response;
	}

	@Override
	public AbstractResponse updateMilk(MilkRequest request) throws DairyException {
		AbstractResponse response = new AbstractResponse();

		Optional<Milk> findById = milkRepository.findById(request.getId());

		Milk milk = converterUtil.convertToMilkEntity(request, findById);
		milk.setPrice(dairyUtil.getPrice(milk.getWeight(), milk.getFat(), milk.getSnf()));
		milk = milkRepository.save(milk);
		response.setData(milk.getId());

		return response;
	}

	@Override
	public AbstractResponse findCustomerMilk(String customerCode, Integer cutomerId) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		List<Milk> milkList = null;
		if (!StringUtils.isEmpty(customerCode)) {
			Optional<Customer> findByCustomerCode = customerRespository.findByCustomerCode(customerCode);

			if (findByCustomerCode.isPresent()) {
				milkList = milkRepository.findMilkByCustomer(findByCustomerCode.get().getId());
			}
		}

		if (!StringUtils.isEmpty(cutomerId)) {
			milkList = milkRepository.findMilkByCustomer(cutomerId);
		}

		if (!StringUtils.isEmpty(milkList)) {
			List<MilkRequest> convertToMilkRequests = converterUtil.convertToMilkRequests(milkList);
			response.setData(convertToMilkRequests);
		} else {
			throw new DairyException("milk does not exist");
		}

		return response;
	}

	@Override
	public AbstractResponse findMilkById(Integer id) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		Optional<Milk> findById = milkRepository.findById(id);
		MilkRequest convertToMilkRequest = converterUtil.convertToMilkRequest(findById);
		response.setData(convertToMilkRequest);
		return response;
	}

	@Override
	public AbstractResponse searchMilkBetweenDates(SearchMilkRequest searchMilkRequest) {
		AbstractResponse response = new AbstractResponse();
		List<Milk> milkList = null;
		if (!StringUtils.isEmpty(searchMilkRequest.getCustomerId())) {
			milkList = milkRepository.findByCustomerAndDateBetween(searchMilkRequest.getCustomerId(),
					converterUtil.stringToLocalDate(searchMilkRequest.getFromDate()),
					converterUtil.stringToLocalDate(searchMilkRequest.getToDate()));
		} else {
			Optional<Customer> findByCustomerCode = customerRespository
					.findByCustomerCode(searchMilkRequest.getCustomerCode());
			milkList = milkRepository.findByCustomerAndDateBetween(findByCustomerCode.get().getId(),
					converterUtil.stringToLocalDate(searchMilkRequest.getFromDate()),
					converterUtil.stringToLocalDate(searchMilkRequest.getToDate()));
		}

		if (!StringUtils.isEmpty(milkList)) {
			List<MilkRequest> convertToMilkRequests = converterUtil.convertToMilkRequests(milkList);
			response.setData(convertToMilkRequests);
		} else {
			response.setError("data not present");
		}
		
		return response;
	}

	@Override
	public AbstractResponse deleteMilkById(Integer id) {
		AbstractResponse response = new AbstractResponse();
		Optional<Milk> findById = milkRepository.findById(id);
		if (findById.isPresent()) {
			milkRepository.delete(findById.get());
			response.setData("Milk deleted");
		}

		return response;
	}

	@Override
	public AbstractResponse deleteMilkByCustomerCode(String customerCode) {
		AbstractResponse response = new AbstractResponse();
		Optional<Customer> findByCustomerCode = customerRespository.findByCustomerCode(customerCode);
		if (findByCustomerCode.isPresent()) {
			List<Milk> findMilkByCustomer = milkRepository.findMilkByCustomer(findByCustomerCode.get().getId());
			milkRepository.deleteAll(findMilkByCustomer);

			response.setData("Milk deleted");
		} else {
			response.setStatus(404);
			response.setData("Customer is not exist :" + customerCode);
		}
		return response;
	}
}
