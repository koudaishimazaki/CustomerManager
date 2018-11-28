package com.shimazaki.springboot.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shimazaki.springboot.entity.Customer;


public class CustomerDto {

	private Customer customer;
	private String created;
	private String updated;

	/**
	 * 全データの変換、格納
	 * @param customer
	 * @return
	 */
	public CustomerDto(Customer origin) {
		// customerを格納
		this.setCustomer(origin);

		// 登録日時をyyyy年MM月dd日 HH時mm分のフォーマットにして格納
		this.setCreated(origin.getCreated());

		// 更新日時をyyyy年MM月dd日 HH時mm分のフォーマットにして格納
		this.setUpdated(origin.getUpdated());
	}

	/**
	 * customerに顧客オリジナルデータを格納
	 * @param originCustomer
	 */
	public void setCustomer(Customer originCustomer) {
		customer = originCustomer;
	}

	/**
	 * 登録日時を見やすいフォーマットに変換して格納
	 * @param originCreated
	 */
	public void setCreated(Date originCreated) {
		if (originCreated == null) {
			created = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
			created = sdf.format(originCreated);
		}
	}

	/**
	 * 更新日時を見やすいフォーマットに変換して格納
	 * @param originUpdated
	 */
	public void setUpdated(Date originUpdated) {
		if (originUpdated == null) {
			updated = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
			updated = sdf.format(originUpdated);
		}
	}

	/**
	 * getter
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}

}
