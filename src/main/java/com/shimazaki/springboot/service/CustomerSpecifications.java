package com.shimazaki.springboot.service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
	 * Fromの値以上を検索
	 * @param string
	 * @return
	 */
	public static Specification<Customer> createdGreaterThanEqual(String createdFrom) {

		//DateTimeFormatter でフォーマット指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

		return StringUtils.isEmpty(createdFrom) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				//文字列を日付に変換
				LocalDate creatFrom = LocalDate.parse(createdFrom, formatter);
				//LocalDate から Date に変換
				Date date = Date.from(creatFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
				return cb.greaterThanOrEqualTo(root.get("created"), date);
			}
		};
	}

	/**
	 * 登録日To
	 * Toの値以下を検索
	 * @param string
	 * @return
	 */
	public static Specification<Customer> createdLessThanEqual(String createdTo) {

		//DateTimeFormatter でフォーマット指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		return StringUtils.isEmpty(createdTo) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				//文字列を日付に変換
				LocalDate creatTo = LocalDate.parse(createdTo, formatter);

				//LocalDate から Date に変換
				Date date = Date.from(creatTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
				return cb.lessThanOrEqualTo(root.get("created"), date);
			}
		};
	}

	/**
	 * 更新日From
	 * Fromの値以上を検索
	 * @param string
	 * @return
	 */
	public static Specification<Customer> updatedGreaterThanEqual(String updatedFrom) {
		//DateTimeFormatter でフォーマット指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

		return StringUtils.isEmpty(updatedFrom) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				//文字列を日付に変換
				LocalDate updateFrom = LocalDate.parse(updatedFrom, formatter);
				//LocalDate から Date に変換
				Date date = Date.from(updateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
				return cb.greaterThanOrEqualTo(root.get("updated"), date);
			}
		};
	}

	/**
	 * 更新日To
	 * Toの値以下を検索
	 * @param string
	 * @return
	 */
	public static Specification<Customer> updatedLessThanEqual(String updatedTo) {
		//DateTimeFormatter でフォーマット指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		return StringUtils.isEmpty(updatedTo) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				//文字列を日付に変換
				LocalDate updateTo = LocalDate.parse(updatedTo, formatter);

				//LocalDate から Date に変換
				Date date = Date.from(updateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
				return cb.lessThanOrEqualTo(root.get("updated"), date);
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
