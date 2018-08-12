package com.rakesh.dairy.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakesh.dairy.exception.DairyException;
import com.rakesh.dairy.model.AbstractResponse;
import com.rakesh.dairy.model.MilkRequest;
import com.rakesh.dairy.model.SearchMilkRequest;
import com.rakesh.dairy.service.MilkService;

@RestController
@RequestMapping("/milk")
public class MilkController {

	@Autowired
	private MilkService milkService;
	
	private static DecimalFormat df2 = new DecimalFormat(".##");

	@PostMapping(value = "/create")
	public ModelAndView saveMilk(@ModelAttribute("createMilkRequest") MilkRequest request) throws DairyException {
		milkService.saveMilk(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/customer/findAll");
		return modelAndView;
	}

	@PostMapping(value = "/update")
	public ModelAndView updateMilk(@ModelAttribute("milkRequest") MilkRequest request) throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/milk/findCustomerMilk?customerCode="+request.getCustomerCode());
		milkService.updateMilk(request);
		return modelAndView;
	}

	@GetMapping(value = "/findCustomerMilk")
	public ModelAndView findAll(@RequestParam(value = "customerCode", required = false) String customerCode,
			@RequestParam(value = "customerId", required = false) Integer customerId) throws DairyException {
		AbstractResponse findCustomerMilk = milkService.findCustomerMilk(customerCode, customerId);
		@SuppressWarnings("unchecked")
		List<MilkRequest> milkList = (List<MilkRequest>) findCustomerMilk.getData();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ListCustomerMilk");
		modelAndView.addObject("milkList", milkList);
		if(!StringUtils.isEmpty(milkList) && milkList.size() > 0) {			
			modelAndView.addObject("customerName", milkList.get(0).getCustomerName());
			modelAndView.addObject("customerCode", milkList.get(0).getCustomerCode());
			modelAndView.addObject("totalWeight", df2.format(milkList.stream().mapToDouble(m -> m.getWeight()).sum()));
			modelAndView.addObject("totalPrice", df2.format(milkList.stream().mapToDouble(m -> m.getPrice()).sum()));
		}
		return modelAndView;
	}

	@GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse findById(@RequestParam("id") Integer id) throws DairyException {
		return milkService.findMilkById(id);
	}

	@PostMapping(value = "/search")
	public ModelAndView searchBetweenDates(@ModelAttribute("searchMilkRequest") SearchMilkRequest searchMilkRequest) {
		AbstractResponse searchMilkBetweenDates = milkService.searchMilkBetweenDates(searchMilkRequest);
		@SuppressWarnings("unchecked")
		List<MilkRequest> milkList = (List<MilkRequest>) searchMilkBetweenDates.getData();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ListCustomerMilk");
		modelAndView.addObject("milkList", milkList);
		
		if(!StringUtils.isEmpty(milkList) && milkList.size() > 0) {			
			modelAndView.addObject("customerName", milkList.get(0).getCustomerName());
			modelAndView.addObject("customerCode", milkList.get(0).getCustomerCode());
			modelAndView.addObject("totalWeight", df2.format(milkList.stream().mapToDouble(m -> m.getWeight()).sum()));
			modelAndView.addObject("totalPrice", df2.format(milkList.stream().mapToDouble(m -> m.getPrice()).sum()));
		}
		return modelAndView;
	}

	@GetMapping(value = "/delete")
	public ModelAndView delete(@RequestParam("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		milkService.deleteMilkById(id);
		modelAndView.setViewName("redirect:/milk/findCustomerMilk?customerId="+id);
		return modelAndView;
	}

	@GetMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public AbstractResponse deleteAll(@RequestParam("customerCode") String customerCode) {
		return milkService.deleteMilkByCustomerCode(customerCode);
	}
	
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id") Integer id) throws DairyException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EditMilk");
		AbstractResponse findMilkById = milkService.findMilkById(id);
		modelAndView.addObject("milkRequest", findMilkById.getData());
		return modelAndView;
	}
}
