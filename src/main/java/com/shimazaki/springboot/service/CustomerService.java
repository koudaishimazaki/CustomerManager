package com.shimazaki.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.shimazaki.springboot.dto.CustomerSearchConditions;
import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;

@Service
public class CustomerService {

	//1ページの最大表示件数
	private static final int PAGE_SIZE = 15;

	@Autowired
	CustomerRepository repository;

	@PersistenceContext
	private EntityManager entityManager;


	public static int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public Page<Customer> findCustomers(CustomerSearchConditions condition, Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
		Page<Customer> results = repository.findAll(Specifications
				.where(CustomerSpecifications.firstNameContains(condition.getFirst_name()))
				.and(CustomerSpecifications.lastNameContains(condition.getLast_name()))
				.and(CustomerSpecifications.f_name_kanaContains(condition.getF_name_kana()))
				.and(CustomerSpecifications.l_name_kanaContains(condition.getL_name_kana()))
				.and(CustomerSpecifications.tellContains(condition.getTell()))
				.and(CustomerSpecifications.mailContains(condition.getMail()))
				.and(CustomerSpecifications.postal_codeContains(condition.getPostal_code()))
				.and(CustomerSpecifications.stateContains(condition.getState()))
				.and(CustomerSpecifications.cityContains(condition.getCity()))
				.and(CustomerSpecifications.addressContains(condition.getAddress()))
				.and(CustomerSpecifications.deletedIsNull())
				, pageRequest);
		return results;
	}


}
