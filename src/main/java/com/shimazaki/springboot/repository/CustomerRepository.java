package com.shimazaki.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shimazaki.springboot.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	/**
	 * 検索フィールドサジェスト用SQL
	 */
	//姓 部分検索SQL
	@Query(value = "SELECT first_name FROM customer WHERE first_name LIKE :first_name% LIMIT 10", nativeQuery = true)
	public List<String> findByFirstNameLikeLimit10(@Param("first_name") String firstName);

	//名 部分検索SQL
	@Query(value = "SELECT last_name FROM customer WHERE last_name LIKE :last_name% LIMIT 10", nativeQuery = true)
	public List<String> findByLastNameLikeLimit10(@Param("last_name") String lastName);

	//姓(かな) 部分検索SQL
	@Query(value = "SELECT f_name_kana FROM customer WHERE f_name_kana LIKE :f_name_kana% LIMIT 10", nativeQuery = true)
	public List<String> findByFirstNameKanaLikeLimit10(@Param("f_name_kana") String firstNameKana);

	//姓(かな) 部分検索SQL
	@Query(value = "SELECT l_name_kana FROM customer WHERE l_name_kana LIKE :l_name_kana% LIMIT 10", nativeQuery = true)
	public List<String> findByLastNameKanaLikeLimit10(@Param("l_name_kana") String lastNameKana);


}
