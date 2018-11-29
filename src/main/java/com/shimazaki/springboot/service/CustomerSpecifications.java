package com.shimazaki.springboot.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.shimazaki.springboot.entity.Customer;

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
	 * @param f_name_kana
	 * @return
	 */
	public static Specification<Customer> f_name_kanaContains(String f_name_kana) {
		return StringUtils.isEmpty(f_name_kana) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("f_name_kana"), "%" + f_name_kana + "%");
			}
		};
	}


	/**
	 * 名(かな)の検索
	 * @param l_name_kana
	 * @return
	 */
	public static Specification<Customer> l_name_kanaContains(String l_name_kana) {
		return StringUtils.isEmpty(l_name_kana) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("l_name_kana"), "%" + l_name_kana + "%");
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
	public static Specification<Customer> postal_codeContains(String postal_code) {
		return StringUtils.isEmpty(postal_code) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("postal_code"), "%" + postal_code + "%");
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

	/**
	 * 未削除（deleted == null）のデータ検索
	 * @return
	 */
	public static Specification<Customer> deletedIsNull() {
		return new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.isNull(root.get("deleted"));
			}
		};
	}

}
