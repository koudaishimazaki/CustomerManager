package com.shimazaki.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.AreaRepository;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.repository.ListRepository;
import com.shimazaki.springboot.service.CustomerService;

@Controller
@RequestMapping("/index")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	ListRepository listRepository;

	private static final String VIEW = "index";

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView customerList(ModelAndView mov,
						@RequestParam(name="first_name", required=false) String first_name,
						@RequestParam(name="last_name", required=false) String last_name,
						@RequestParam(name="f_name_kana", required=false) String f_name_kana,
						@RequestParam(name="l_name_kana", required=false) String l_name_kana,
						@RequestParam(name="tell", required=false) String tell,
						@RequestParam(name="mail", required=false) String mail,
						@RequestParam(name="postal_code", required=false) String postal_code,
						@RequestParam(name="state", required=false) String state,
						@RequestParam(name="city", required=false) String city,
						@RequestParam(name="address", required=false) String address) {

		mov.setViewName(VIEW);
		mov.addObject("first_name", first_name);
		mov.addObject("last_ame", last_name);
		mov.addObject("f_name_kana", f_name_kana);
		mov.addObject("l_name_kana", l_name_kana);
		mov.addObject("tell", tell);
		mov.addObject("mail", mail);
		mov.addObject("postal_code", postal_code);
		mov.addObject("state", state);
		mov.addObject("city", city);
		mov.addObject("address", address);
		List<Customer> customers = customerService.findCustomers(first_name, last_name, f_name_kana, l_name_kana, tell, mail, postal_code, state, city, address);
		mov.addObject("customers", customers);
		mov.addObject("customerSize", customers.size());

		return mov;

	}





	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ModelAndView login(ModelAndView mov
			, @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name) {
		mov.setViewName("/list");
		mov.addObject("first_name", first_name);
		mov.addObject("last_name", last_name);
		List<Customer> result = customerService.search(first_name, last_name);
		mov.addObject("result", result);
		mov.addObject("resultSize", result.size());
	    return mov;

	}


}
