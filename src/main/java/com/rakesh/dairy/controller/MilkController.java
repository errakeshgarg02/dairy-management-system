package com.rakesh.dairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.CreateMilkRequest;
import com.rakesh.dairy.service.MilkService;

@RestController
@RequestMapping("/milk")
public class MilkController {
	
	@Autowired
	private MilkService milkService;
	
	@PostMapping(value="/create", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE )
	public AbstractResponse saveMilk(@RequestBody CreateMilkRequest request) throws DairyException {
		return milkService.saveMilk(request);
	}

	
}
