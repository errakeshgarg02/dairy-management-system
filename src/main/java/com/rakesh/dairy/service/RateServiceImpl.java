package com.rakesh.dairy.service;

import java.util.List;
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
	public AbstractResponse updateRate(RateRequest rateRequest) throws DairyException {
		AbstractResponse response = new AbstractResponse();
		Optional<Rate> findById = repository.findById(rateRequest.getId());
		
		if(findById.isPresent()) {
			Rate rate = converterUtil.convertToRateEntity(rateRequest, findById.get());
			rate = repository.save(rate);
			response.setData(rate.getId());
		} 
		return response;
	}

	@Override
	public AbstractResponse findAll() throws DairyException {
		AbstractResponse response = new AbstractResponse();
		List<Rate> findAll = repository.findAll();
		List<RateRequest> convertToRateRequest = converterUtil.convertToRateRequest(findAll);
		response.setData(convertToRateRequest);
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
		Optional<Rate> findById = repository.findById(id);
		RateRequest rateRequest = converterUtil.convertToRateRequest(findById);
		response.setData(rateRequest);
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
