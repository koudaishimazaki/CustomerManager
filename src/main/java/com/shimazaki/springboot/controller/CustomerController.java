package com.shimazaki.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shimazaki.springboot.dto.CustomerDto;
import com.shimazaki.springboot.dto.CustomerSearchConditions;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.AreaRepository;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerService customerService;

	@Autowired
	AreaRepository areaRepository;


	/**
	 * 検索ページ初期表示
	 * @param mav
	 * @return
	 */
	@RequestMapping("/index/page={pagenumber}")
	public ModelAndView searchTop(@PathVariable Integer pagenumber, ModelAndView mav) {

		//index.htmlを適用
		mav.setViewName("/index");


		// 検索条件null
		CustomerSearchConditions condition = null;

		// 顧客情報取得
		Page<Customer> page = customerService.findCustomers(condition, pagenumber);
		List<CustomerDto> customerList = this.getSearchResult(page);

		// 顧客リストを反映
		mav.addObject("customer_list", customerList);

		return mav;
	}

	/**
	 * 顧客検索
	 * @param condition
	 * @return
	 */
	private List<CustomerDto> getSearchResult(Page<Customer> page) {

		List<CustomerDto> convertCustomerList = new ArrayList<CustomerDto>();
		for (Customer customer: page) {
			CustomerDto customerDto = new CustomerDto(customer);

			// 文字列変換したデータをリストに格納
			convertCustomerList.add(customerDto);
		}
		return convertCustomerList;
	}

}
