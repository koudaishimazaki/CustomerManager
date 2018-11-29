package com.shimazaki.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimazaki.springboot.entity.Customer;

public interface ListRepository extends JpaRepository<Customer, Long> {

}
