package com.shimazaki.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shimazaki.springboot.dto.SearchDto;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.service.CustomerService;
import com.shimazaki.springboot.utility.MyTLUtility;


@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	MyTLUtility myTLUtility;


	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/customer/search/page=1";
	}


	/**
	 * 顧客データの格納
	 * @param page
	 * @return
	 */
	private List<SearchDto> getSearchResult(Page<Customer> page) {

		List<SearchDto> customerList = new ArrayList<SearchDto>();
		for (Customer customer: page) {
			SearchDto searchDto = new SearchDto(customer);

			customerList.add(searchDto);
		}
		return customerList;
	}


	/**
	 * 一覧画面初期表示
	 * @param pagenumber
	 * @param mov
	 * @return
	 */
	@RequestMapping("/customer/search/page={pagenumber}")
	public ModelAndView customerList(@PathVariable Integer pagenumber, ModelAndView mov) {

		mov.setViewName("/customer/index");

		//顧客全件検索
		SearchDto search = null;
		Page<Customer> page = customerService.findCustomers(search, pagenumber);
		List<SearchDto> customerList = this.getSearchResult(page);

		// 顧客リストを反映
		mov.addObject("index", customerList);

		return mov;

	}




//	/**
//	 * ページネーション
//	 * @param model
//	 * @param pageable
//	 * @return
//	 */
//	@RequestMapping(value="/index", method=RequestMethod.GET)
//	public String getCustomerList(Model model, Pageable pageable) {
//
//		Page<Customer> customerPage = customerService.getAllCustomer(pageable);
//		model.addAttribute("page", customerPage);
//		model.addAttribute("customers", customerPage.getContent());
//		model.addAttribute("url", "/customer/customerList");
//
//		return "/customer/customerList";
//
//	}


}
