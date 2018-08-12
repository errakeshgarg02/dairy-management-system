package com.rakesh.dairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.RateRequest;
import com.rakesh.dairy.service.RateService;

@RestController
@RequestMapping("/rate")
public class RateController {
	
	@Autowired
	private RateService rateService;
	
	@PostMapping(value="/create")
	public ModelAndView createRate(@ModelAttribute("createRate") RateRequest request) throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		rateService.createRate(request);
		modelAndView.setViewName("redirect:/rate/addRatePage");
		return modelAndView;
	}
	
	@PostMapping(value="/update")
	public ModelAndView updateRate(@ModelAttribute("updateRate") RateRequest request) throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		rateService.updateRate(request);
		modelAndView.setViewName("redirect:/rate/findAll");
		return modelAndView;
	}
	
	@GetMapping(value="/findAll")
	public ModelAndView findAll() throws DairyException {		
		AbstractResponse findAll = rateService.findAll();
		ModelAndView modelAndView = new ModelAndView("Rates","rates",findAll.getData());
		return modelAndView;
	}
	
	@GetMapping(value="/edit")
	public ModelAndView findById(@RequestParam("id") Integer id ) throws DairyException {
		AbstractResponse findById = rateService.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EditRate");
		modelAndView.addObject("updateRate",findById.getData());
		return modelAndView;
	}
	
	@GetMapping(value="/delete")
	public ModelAndView delete(@RequestParam("id") Integer id) throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/rate/findAll");
		rateService.deleteById(id);
		return modelAndView; 
	}
	
	@GetMapping(value="/truncate")
	public ModelAndView truncateRate() throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		rateService.truncate();
		modelAndView.setViewName("redirect:/rate/findAll");
		return modelAndView;
	}

}
