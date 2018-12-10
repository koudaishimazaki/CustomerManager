package com.shimazaki.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shimazaki.springboot.dto.SearchDto;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.service.CustomerService;


@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;


	/**
	 *動作確認用
	 * @return
	 */
	@RequestMapping("/test")
	public String test() {
		return "index";
	}


	/**
	 * アプリケーション起動時、
	 * 顧客一覧初期画面へリダイレクト
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/customer/customer_list/page=1";
	}


	/**
	 * 顧客一覧画面初期表示
	 * 全件検索結果の一覧画面を表示
	 * @param pagenumber
	 * @param mov
	 * @return
	 */
	@RequestMapping("/customer/customer_list/page={pagenumber}")
	public ModelAndView customerList(@PathVariable Integer pagenumber, ModelAndView mov) {

		//customer_list.htmlをテンプレートに指定
		mov.setViewName("/customer/customer_list");

		//全件データ取得
		SearchDto search = new SearchDto();
		Page<Customer> page = customerService.findCustomers(search, pagenumber);

		// 検索結果を格納
		mov.addObject("customer_list", page);

		return mov;

	}


	/**
	 * 検索ボタン押下時
	 * 検索条件を適用した検索結果を表示する
	 * @param search
	 * @param pagenumber
	 * @param mov
	 * @return
	 */
	@RequestMapping(value = "/customer/search/page={pagenumber}",  method = RequestMethod.GET)
	public ModelAndView searchResults(@ModelAttribute SearchDto search, @PathVariable Integer pagenumber, ModelAndView mov) {

		//customer_list.htmlをテンプレートに指定
		mov.setViewName("/customer/customer_list");

		// 検索条件を反映
		mov.addObject("search", search);

		//検索データ取得
		Page<Customer> page = customerService.findCustomers(search, pagenumber);

		// 検索結果を格納
		mov.addObject("customer_list", page);

		return mov;

	}


	/**
	 * 登録ページ初期表示
	 * @param mav
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/customer/entry")
	public ModelAndView entry(ModelAndView mav, Pageable pageable) {

		//entry.htmlをテンプレートに指定
		mav.setViewName("/customer/entry");

		return mav;
	}

}
