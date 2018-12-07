package com.shimazaki.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerService customerService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}


}
