package com.shimazaki.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shimazaki.springboot.dao.CustomerDaoImpl;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.AreaRepository;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.repository.ListRepository;
import com.shimazaki.springboot.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	ListRepository listRepository;

	@Autowired
	CustomerDaoImpl dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		Iterable<Customer> list = dao.getAll();
		mav.addObject("datalist", list);
		return mav;
	}



//	private static final String VIEW = "/list";
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String index() {
//		return VIEW;
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView login(ModelAndView mov
//			, @RequestParam("id") String id, @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name) {
//		mov.setViewName(VIEW);
//		mov.addObject("id", id);
//		mov.addObject("first_name", first_name);
//		mov.addObject("last_name", last_name);
//		List<Customer> result = customerService.search(id, first_name, last_name);
//		mov.addObject("result", result);
//		mov.addObject("resultSize", result.size());
//	    return mov;
//
//	}


}
