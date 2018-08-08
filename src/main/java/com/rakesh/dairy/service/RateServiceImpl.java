package com.rakesh.dairy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.dao.RateRepository;
import com.rakesh.dairy.entity.Rate;
import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.model.UpdateRateRequest;
import com.rakesh.dairy.util.ConverterUtil;

@Service
public class RateServiceImpl implements RateService {
	
	@Autowired
	private RateRepository repository;
	
	@Autowired
	private ConverterUtil converterUtil;

	@Override
	public AbstractResponse createRate(RateRequest rateRequest) throws DairyException {
		AbstractResponse response = new AbstractResponse(); 
		Rate rate = converterUtil.convertToRateEntity(rateRequest);
		rate = repository.save(rate);
		response.setData(rate.getId());
		return response;
	}

	@Override
	public AbstractResponse updateRate(UpdateRateRequest rateRequest) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		if(StringUtils.isEmpty(rateRequest) || StringUtils.isEmpty(rateRequest.getId())) {
			throw new DairyException("Rate id can not be null");
		}
		Optional<Rate> findById = repository.findById(rateRequest.getId());
		
		if(findById.isPresent()) {
			Rate rate = converterUtil.convertToRateEntity(rateRequest, findById.get());
			rate = repository.save(rate);
			response.setData(rate.getId());
		} else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setData("Object not found");
		}
		
		return response;
	}

	@Override
	public AbstractResponse findAll() throws DairyException {
		AbstractResponse response = new AbstractResponse();
		response.setData(repository.findAll());
		return response;
	}

	@Override
	public AbstractResponse deleteById(Integer id) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		repository.deleteById(id);
		response.setData("data deleted");
		return response;
	}

	@Override
	public AbstractResponse findById(Integer id) {
		AbstractResponse response = new AbstractResponse();
		response.setData(repository.findById(id));
		return response;
	}

	@Override
	public AbstractResponse truncate() {
		AbstractResponse response = new AbstractResponse();
		repository.deleteAll();
		response.setData("All record deleted");
		return response;
	}

}
