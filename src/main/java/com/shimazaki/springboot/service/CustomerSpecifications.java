package com.shimazaki.springboot.service;

import java.util.Date;

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

	/**
	 * 登録日From
	 * @param createdFrom
	 * @return
	 */
	public static Specification<Customer> createdGreaterThanEqual(Date createdFrom) {
		return StringUtils.isEmpty(createdFrom) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.get("created"), createdFrom);
			}
		};
	}

	/**
	 * 登録日To
	 * @param createdTo
	 * @return
	 */
	public static Specification<Customer> createdLessThanEqual(Date createdTo) {
		return StringUtils.isEmpty(createdTo) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.get("created"), createdTo);
			}
		};
	}

	/**
	 * 更新日From
	 * @param updatedFrom
	 * @return
	 */
	public static Specification<Customer> updatedGreaterThanEqual(Date updatedFrom) {
		return StringUtils.isEmpty(updatedFrom) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.get("updated"), updatedFrom);
			}
		};
	}

	/**
	 * 更新日To
	 * @param updatedTo
	 * @return
	 */
	public static Specification<Customer> updatedLessThanEqual(Date updatedTo) {
		return StringUtils.isEmpty(updatedTo) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.get("updated"), updatedTo);
			}
		};
	}


	/**
	 * 未削除（deleted == null）の検索
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
