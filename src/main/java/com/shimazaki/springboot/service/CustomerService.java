package com.shimazaki.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.shimazaki.springboot.dto.SearchDto;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;;


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
	public Page<Customer> findCustomers(SearchDto search, Integer pageNumber) {

		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);

		Page<Customer> page = repository.findAll(Specifications
				.where(CustomerSpecifications.first_nameContains(search.getFirst_name()))
				.and(CustomerSpecifications.last_nameContains(search.getLast_name()))
				.and(CustomerSpecifications.f_name_kanaContains(search.getF_name_kana()))
				.and(CustomerSpecifications.l_name_kanaContains(search.getL_name_kana()))
				.and(CustomerSpecifications.tellContains(search.getTell()))
				.and(CustomerSpecifications.mailContains(search.getMail()))
				.and(CustomerSpecifications.postal_codeContains(search.getPostal_code()))
				.and(CustomerSpecifications.stateContains(search.getState()))
				.and(CustomerSpecifications.cityContains(search.getCity()))
				.and(CustomerSpecifications.addressContains(search.getAddress()))
				, pageRequest);

		 return page;

	}


//	/**
//	 * ページネーション
//	 * @param pageable
//	 * @return
//	 */
//	public Page<Customer> getAllCustomer(Pageable pageable) {
//
//        return repository.findAll(pageable);
//    }


}
