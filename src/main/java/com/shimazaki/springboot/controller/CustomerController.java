package com.shimazaki.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shimazaki.springboot.dto.CustomerDto;
import com.shimazaki.springboot.dto.SearchDto;
import com.shimazaki.springboot.entity.Area;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.AreaRepository;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.service.AreaService;
import com.shimazaki.springboot.service.CustomerService;
import com.shimazaki.springboot.service.PageWrapper;


@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	AreaService areaService;


//	/**
//	 *動作確認用
//	 * @return
//	 */
//	@RequestMapping("/test")
//	public String test() {
//		return "index";
//	}


//-----------* マッピングメソッド *---------------//

	/**
	 * アプリケーション起動時、
	 * 顧客一覧画面へリダイレクト
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/customer/customer_list";
	}


	/**
	 * 顧客一覧ページ
	 * 初期表示は全件検索結果の表示
	 * 検索ボタン押下時は検索条件を適用した検索結果を表示する
	 * @param search
	 * @param pagenumber
	 * @param mov
	 * @return
	 */
	@RequestMapping(value = "customer/customer_list")
	public ModelAndView searchResults(@ModelAttribute SearchDto search, Pageable pageable, ModelAndView mov) {

		//customer_list.htmlをテンプレートに指定
		mov.setViewName("/customer/customer_list");

		// 都道府県ドロップリストに都道府県データを反映
		mov.addObject("area_list", this.getStateList());

		// 検索条件を反映
		mov.addObject("search", search);

		//検索データ取得
		Page<Customer> page = customerService.findCustomers(search, pageable);

		//データを表示用にフォーマットして格納
		List<CustomerDto> customerList = this.getSearchResult(page);

		//ページネーションの作成
		PageWrapper<Customer> wrapper = new PageWrapper<Customer>(page, "/customer/customer_list");

		// 検索結果の反映
		mov.addObject("customer_list", customerList);

		//ページネーションの反映
		mov.addObject("customerSize", page.getNumberOfElements());
		mov.addObject("page", wrapper);

		return mov;
	}

	/**
	 * 登録ページ初期表示
	 * @param mav
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/customer/entry")
	public ModelAndView entry(@ModelAttribute("customer") Customer customer, ModelAndView mav) {

		// 都道府県ドロップリストに都道府県データを反映
		mav.addObject("area_list", this.getStateList());

		//entry.htmlをテンプレートに指定
		mav.setViewName("/customer/entry");

		mav.addObject("customer", customer);

		return mav;
	}

	/**
	 * 登録内容確認ページ
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/customer/entry/check", method = RequestMethod.POST)
	public ModelAndView checkEntry(@ModelAttribute("customer") @Validated Customer customer, BindingResult result,RedirectAttributes attributes) {

		ModelAndView mav = new ModelAndView();
		attributes.addFlashAttribute("customer", customer);

		boolean err = result.hasErrors();

		String id = null;

		boolean postalError = isPostalCodeCheck(customer.getPostalCode());

		// バリデーションエラーがない場合確認ページへ遷移
		if (!result.hasErrors() && postalError == false) {
			// check.htmlを適用
			mav.setViewName("redirect:/customer/" + id + "/check");

		// バリデーションエラーがある場合登録ページへリダイレクト
		} else {
			mav.setViewName("redirect:/customer/entry");
			attributes.addFlashAttribute("postalError", postalError);
			attributes.addFlashAttribute("fields", err);
		}
		attributes.addFlashAttribute("postalError", postalError);
		attributes.addFlashAttribute("fields", err);

		return mav;
	}

	/**
	 * 確認ページ
	 * @return
	 */
	@RequestMapping(value = "/customer/{id}/check", method = RequestMethod.GET)
	public ModelAndView check(@ModelAttribute("customer") @Validated Customer customer, BindingResult result, @PathVariable String id, @ModelAttribute("postalError") boolean postalError, ModelAndView mav) {

		// check.htmlを適用
		mav.setViewName("/customer/check");

		return mav;
	}


	/**
	 * 編集内容確認ページ
	 * @param id
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/customer/updated/check", method = RequestMethod.POST)
	public ModelAndView checkUpdated(@ModelAttribute("customer") @Validated Customer customer, BindingResult result,RedirectAttributes attributes) {

		ModelAndView mav = new ModelAndView();
		attributes.addFlashAttribute("customer", customer);

		//郵便番号エラーチェック
		boolean postalError = isPostalCodeCheck(customer.getPostalCode());

		// バリデーションエラーがない場合確認ページへ遷移
		if (!result.hasErrors() && postalError == false) {
			// check.htmlを適用
			mav.setViewName("redirect:/customer/" + customer.getId() + "/check");

		// バリデーションエラーがある場合編集ページへリダイレクト
		} else {
			mav.setViewName("redirect:/customer/" + customer.getId() + "/updated");
			attributes.addFlashAttribute("postalError", postalError);
		}
		attributes.addFlashAttribute("postalError", postalError);

		return mav;
	}


	/**
	 * データの登録処理
	 * OKボタン押下時
	 * DBへ登録して顧客詳細ページへリダイレクト
	 * @param customer
	 * @param mav
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/customer/save", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public String save(@ModelAttribute("customer") Customer customer, ModelAndView mav, RedirectAttributes attributes) {

		//IDがnullなら現在日時を取得し登録日に格納
		if (customer.getId() == null) {
			customer.setCreated(new Date());
		}

		//現在日時を取得し更新日に格納
		customer.setUpdated(new Date());

		//データの登録
		customerRepository.saveAndFlush(customer);
		attributes.addAttribute(customer);

		return "redirect:/customer/customer_list";
	}


	/**
	 * 詳細ページ
	 * 顧客名リンク押下時
	 * @return
	 */
	@RequestMapping("/customer/{id}/private")
	public ModelAndView detail(@PathVariable Long id, ModelAndView mav) {

		// private.htmlを適用
		mav.setViewName("/customer/private");

		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.getOne(id);

		// CustomerDtoオブジェクトに移す
		CustomerDto customerDto = new CustomerDto(customer);

		// 顧客データを反映
		mav.addObject("customer", customerDto);

		return mav;
	}


	/**
	 * 編集ページ
	 * 編集ボタン押下時
	 * @return
	 */
	@RequestMapping("/customer/{id}/updated")
	public ModelAndView updated(@PathVariable Long id, ModelAndView mav) {

		// updated.htmlを適用
		mav.setViewName("/customer/updated");

		// 都道府県ドロップリストに都道府県データを反映
		mav.addObject("area_list", this.getStateList());

		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.getOne(id);

		// 顧客データを反映
		mav.addObject("customer", customer);

		return mav;
	}


	/**
	 * 削除ボタン押下時
	 * 削除フラグで論理削除
	 * @param id
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/customer/{id}/delete", method = RequestMethod.POST)
	@Transactional
	public String deleted(@PathVariable Long id, RedirectAttributes attributes) {

		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.getOne(id);

		//削除フラグをtrueにして保存
		customer.setDeleted(true);
		customerRepository.save(customer);

		attributes.addAttribute(customer);

		return "redirect:/customer/customer_list";
	}


//--------------* サジェストメソッド *--------------//

	/**
	 * 姓サジェスト
	 * @param firstNameSearchData
	 * @return
	 */
	@RequestMapping(value = "/f_name/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> firstNameSuggest(@RequestParam String firstNameSearchData) {

		//部分一致で10件分検索
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

		//部分一致で10件分検索
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

		//部分一致で10件分検索
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

		//部分一致で10件分検索
		List<String> kanaList = customerRepository.findByLastNameKanaLikeLimit10(lastKanaSearchData);

		return kanaList;
	}


	/**
	 * 住所自動入力
	 * @param postal_code
	 * @return
	 */
	@RequestMapping(value = "/getAddressList", method = RequestMethod.GET)
	@ResponseBody
	public Area getMatchingAddress(@RequestParam String postalCode) {

		//areaテーブルから郵便番号検索
		Area resultArea = areaRepository.findByPostalCode(postalCode);
		return resultArea;
	}


//-------------* privateメソッド *-----------------//

	/**
	 * 都道府県データ取得
	 * @return
	 */
	private List<String> getStateList() {

		return areaRepository.getStates();
	}


	/**
	 * 登録日、更新日のフォーマット
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
	 * 郵便番号チェック
	 * trueでエラーを返す
	 * @param postalCode
	 * @return
	 */
	private boolean isPostalCodeCheck(String postalCode) {

		// false判定 : 郵便番号がnullまたは空欄
		if (postalCode == null || postalCode.length() == 0) {
			return false;
		} else {

			Area exist = areaRepository.findByPostalCode(postalCode);

			// true判定 : 郵便番号が存在しない
			if (exist == null) {
				return true;

			// false判定 : 郵便番号が存在する
			} else {
				return false;
			}
		}
	}

}
