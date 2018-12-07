package com.shimazaki.springboot.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.shimazaki.springboot.entity.Customer;


/**
 * 各検索条件の設定
 * 全て部分一致で検索する
 * @author wizuser
 *
 */
public class CustomerSpecifications {

	/**
	 * 姓の検索
	 * @param firstName
	 * @return
	 */
	public static Specification<Customer> firstNameContains(String firstName) {
		return StringUtils.isEmpty(firstName) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("firstName"), "%" + firstName + "%");
			}
		};
	}

	/**
	 * 名の検索
	 * @param lastName
	 * @return
	 */
	public static Specification<Customer> lastNameContains(String lastName) {
		return StringUtils.isEmpty(lastName) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("lastName"), "%" + lastName + "%");
			}
		};
	}

	/**
	 * 姓(かな)の検索
	 * @param firstNameKana
	 * @return
	 */
	public static Specification<Customer> firstNameKanaContains(String firstNameKana) {
		return StringUtils.isEmpty(firstNameKana) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("firstNameKana"), "%" + firstNameKana + "%");
			}
		};
	}


	/**
	 * 名(かな)の検索
	 * @param lastNameKana
	 * @return
	 */
	public static Specification<Customer> lastNameKanaContains(String lastNameKana) {
		return StringUtils.isEmpty(lastNameKana) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("lastNameKana"), "%" + lastNameKana + "%");
			}
		};
	}

	/**
	 * 電話番号検索
	 * @param tell
	 * @return
	 */
	public static Specification<Customer> tellContains(String tell) {
		return StringUtils.isEmpty(tell) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("tell"), "%" + tell + "%");
			}
		};
	}

	/**
	 * メールアドレス検索
	 * @param mail
	 * @return
	 */
	public static Specification<Customer> mailContains(String mail) {
		return StringUtils.isEmpty(mail) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("mail"), "%" + mail + "%");
			}
		};
	}

	/**
	 * 郵便番号検索
	 * @param postal_code
	 * @return
	 */
	public static Specification<Customer> postalCodeContains(String postalCode) {
		return StringUtils.isEmpty(postalCode) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("postalCode"), "%" + postalCode + "%");
			}
		};
	}

	/**
	 * 都道府県検索
	 * @param state
	 * @return
	 */
	public static Specification<Customer> stateContains(String state) {
		return StringUtils.isEmpty(state) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state"), state);
			}
		};
	}

	/**
	 * 市町村の検索
	 * @param city
	 * @return
	 */
	public static Specification<Customer> cityContains(String city) {
		return StringUtils.isEmpty(city) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("city"), "%" + city + "%");
			}
		};
	}

	/**
	 * 番地の検索
	 * @param address
	 * @return
	 */
	public static Specification<Customer> addressContains(String address) {
		return StringUtils.isEmpty(address) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("address"), "%" + address + "%");
			}
		};
	}

}
