package com.rakesh.dairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.model.UpdateRateRequest;
import com.rakesh.dairy.service.RateService;

@RestController
@RequestMapping("/rate")
public class RateController {
	
	@Autowired
	private RateService rateService;
	
	@PostMapping(value="/create", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse createRate(@RequestBody RateRequest request) throws DairyException {
		return rateService.createRate(request);
	}
	
	@PostMapping(value="/update", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse updateRate(@RequestBody UpdateRateRequest request) throws DairyException {
		return rateService.updateRate(request);
	}
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse findAll() throws DairyException {
		return rateService.findAll();
	}
	
	@GetMapping(value="/find", produces=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse findById(@RequestParam("id") Integer id ) throws DairyException {
		return rateService.findById(id);
	}
	
	@GetMapping(value="/delete", produces=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse delete(@RequestParam("id") Integer id) throws DairyException {
		return rateService.deleteById(id);
	}
	
	@GetMapping(value="/truncate", produces=MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse truncateRate() throws DairyException {
		return rateService.truncate();
	}

}
