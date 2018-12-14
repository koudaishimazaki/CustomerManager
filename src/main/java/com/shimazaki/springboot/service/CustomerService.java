package com.shimazaki.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.shimazaki.springboot.dto.SearchDto;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;


/**
 * サービスクラス
 * 顧客検索とページ分け
 * @author wizuser
 *
 */
@SuppressWarnings({ "deprecation" })
@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	@PersistenceContext
	private EntityManager entityManager;


	//1ページの最大表示件数
	private static final int PAGE_SIZE = 15;

	public static int getPAGE_SIZE() {
		return PAGE_SIZE;
	}


	/**
	 * 検索フォームの値を取得して検索
	 * 検索結果をページ分けして返す
	 * @param first_name
	 * @param last_name
	 * @param f_name_kana
	 * @param l_name_kana
	 * @param tell
	 * @param mail
	 * @param postal_code
	 * @param state
	 * @param city
	 * @param address
	 * @return
	 */
	public Page<Customer> findCustomers(SearchDto search,Pageable pageable) {

//		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);

		Page<Customer> page = repository.findAll(Specifications
				.where(CustomerSpecifications.firstNameContains(search.getFirstName()))
				.and(CustomerSpecifications.lastNameContains(search.getLastName()))
				.and(CustomerSpecifications.firstNameKanaContains(search.getFirstNameKana()))
				.and(CustomerSpecifications.lastNameKanaContains(search.getLastNameKana()))
				.and(CustomerSpecifications.tellContains(search.getTell()))
				.and(CustomerSpecifications.mailContains(search.getMail()))
				.and(CustomerSpecifications.postalCodeContains(search.getPostalCode()))
				.and(CustomerSpecifications.stateContains(search.getState()))
				.and(CustomerSpecifications.cityContains(search.getCity()))
				.and(CustomerSpecifications.addressContains(search.getAddress()))
				, pageable);

		int totalPages = page.getTotalPages();

		 return page;

	}

	public Customer save(Customer customer) {
		return repository.saveAndFlush(customer);
	}

}
