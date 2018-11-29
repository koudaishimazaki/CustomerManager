package com.shimazaki.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shimazaki.springboot.entity.Customer;



public class CustomerDaoImpl implements CustomerDao<Customer> {

	public CustomerDaoImpl() {
		super();
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Customer> getAll() {
		List<Customer> list = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		list = (List<Customer>)entityManager.createQuery(query).getResultList();

		return list;
	}

}
