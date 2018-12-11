package com.shimazaki.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shimazaki.springboot.dto.CustomerDto;
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
		mov.setViewName("customer/customer_list");

		//全件データ取得
		SearchDto search = new SearchDto();
		Page<Customer> page = customerService.findCustomers(search, pagenumber);
		List<CustomerDto> customerList = this.getSearchResult(page);

		// 検索結果を格納
		mov.addObject("customer_list", customerList);

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
	@RequestMapping(value = "customer/search/page={pagenumber}",  method = RequestMethod.GET)
	public ModelAndView searchResults(@ModelAttribute SearchDto search, @PathVariable Integer pagenumber, ModelAndView mov) {

		//customer_list.htmlをテンプレートに指定
		mov.setViewName("/customer/customer_list");

		// 検索条件を反映
		mov.addObject("search", search);

		//検索データ取得
		Page<Customer> page = customerService.findCustomers(search, pagenumber);
		List<CustomerDto> customerList = this.getSearchResult(page);

		// 検索結果を格納
		mov.addObject("customer_list", customerList);

		return mov;

	}


	/**
	 * 検索結果のフォーマット
	 * @return
	 */
	private List<CustomerDto> getSearchResult(Page<Customer> page) {

		List<CustomerDto> customerList = new ArrayList<CustomerDto>();
		for (Customer customer: page) {
			CustomerDto customerDto = new CustomerDto(customer);

			// フォーマットしたデータをリストに格納
			customerList.add(customerDto);
		}
		return customerList;
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


	/**
	 * 姓サジェスト
	 * @param firstNameSearchData
	 * @return
	 */
	@RequestMapping(value = "/f_name/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> firstNameSuggest(@RequestParam String firstNameSearchData) {

		List<String> nameList = customerRepository.findByFirstNameLikeLimit10(firstNameSearchData);
		return nameList;
	}

	/**
	 * 名サジェスト
	 * @param lastNameSearchData
	 * @return
	 */
	@RequestMapping(value = "/l_name/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> lastNameSuggest(@RequestParam String lastNameSearchData) {

		List<String> nameList = customerRepository.findByLastNameLikeLimit10(lastNameSearchData);
		return nameList;
	}

	/**
	 * 姓(かな)サジェスト
	 * @param firstKanaSearchData
	 * @return
	 */
	@RequestMapping(value = "/f_kana/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> firstKanaSuggest(@RequestParam String firstKanaSearchData) {

		List<String> kanaList = customerRepository.findByFirstNameKanaLikeLimit10(firstKanaSearchData);
		return kanaList;
	}

	/**
	 * 名(かな)サジェスト
	 * @param lastKanaSearchData
	 * @return
	 */
	@RequestMapping(value = "/l_kana/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> lastKanaSuggest(@RequestParam String lastKanaSearchData) {

		List<String> kanaList = customerRepository.findByLastNameKanaLikeLimit10(lastKanaSearchData);
		return kanaList;
	}


	/**
	 * 詳細ページ
	 * 顧客名リンク押下時
	 * @return
	 */
	@RequestMapping("/customer/{id}/private")
	public ModelAndView detail(@PathVariable Long id, ModelAndView mav) {

		// detail.htmlを適用
		mav.setViewName("/customer/private");

		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.getOne(id);

		// CustomerDtoオブジェクトに移す
		CustomerDto customerDto = new CustomerDto(customer);

		// ビューに顧客データを反映
		mav.addObject("customer", customerDto);

		return mav;
	}

}
