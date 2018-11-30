package com.shimazaki.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;


@SuppressWarnings({ "deprecation" })
@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CustomerSpecifications specification;

	public List<Customer> findCustomers(String first_name, String last_name, String f_name_kana, String l_name_kana,
										 String tell, String mail, String postal_code, String state, String city, String address) {
		return repository.findAll(Specifications
				.where(CustomerSpecifications.first_nameContains(first_name))
				.and(CustomerSpecifications.last_nameContains(last_name))
				.and(CustomerSpecifications.f_name_kanaContains(f_name_kana))
				.and(CustomerSpecifications.l_name_kanaContains(l_name_kana))
				.and(CustomerSpecifications.tellContains(tell))
				.and(CustomerSpecifications.mailContains(mail))
				.and(CustomerSpecifications.postal_codeContains(postal_code))
				.and(CustomerSpecifications.stateContains(state))
				.and(CustomerSpecifications.cityContains(city))
				.and(CustomerSpecifications.addressContains(address))
				);
	}



	public List<Customer> search(String first_name, String last_name) {

		return repository.findAll(Specifications
				.where(CustomerSpecifications.first_nameContains(first_name))
				.and(CustomerSpecifications.last_nameContains(last_name)));
	}


}
