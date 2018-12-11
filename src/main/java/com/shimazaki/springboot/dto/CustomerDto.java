package com.shimazaki.springboot.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shimazaki.springboot.entity.Customer;


/**
 * 顧客データの格納
 * データのフォーマット
 * @author wizuser
 *
 */
public class CustomerDto {

	private Customer customer;
	private String created;
	private String updated;


	public CustomerDto(Customer origin) {

		// customerを格納
		this.setCustomer(origin);
		//フォーマットした登録日時を格納
		this.setCreated(origin.getCreated());
		//フォーマットした更新日時を格納
		this.setUpdated(origin.getUpdated());
	}


	/**
	 * 顧客データの格納
	 * @param originCustomer
	 */
	public void setCustomer(Customer originCustomer) {
		customer = originCustomer;
	}


	/**
	 * 登録日時をフォーマット
	 * 西暦4桁 0詰め表記
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
	 * 更新日時をフォーマット
	 * 西暦4桁 0詰め表記
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


	//getter
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
