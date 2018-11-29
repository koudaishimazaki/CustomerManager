package com.shimazaki.springboot.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;
import com.shimazaki.springboot.repository.ListRepository;

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



	@Autowired
	ListRepository listRepository;
	public List<Customer> search(Long id, String first_name, String last_name, String f_name_kana, String l_name_kana,
								  String tell, String mail, String postal_code, String state, String city,
								  String address, Date created, Date updated) {
		List<Customer> result = repository.findAll();
		return result;
	}

}
