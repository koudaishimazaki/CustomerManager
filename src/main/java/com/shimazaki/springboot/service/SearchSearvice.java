package com.shimazaki.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.shimazaki.springboot.entity.Customer;
import com.shimazaki.springboot.repository.CustomerRepository;

@Service
public class SearchSearvice {

	@Autowired
	CustomerRepository customerrepository;

	public List<customer> findDatas(String)

}
