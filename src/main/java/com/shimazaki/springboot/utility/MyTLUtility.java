package com.shimazaki.springboot.utility;

import com.shimazaki.springboot.service.CustomerService;

public class MyTLUtility {

	/**
	 * 現在ページから前のページ数を出す
	 * @param page
	 * @return
	 */
	public int prev(int page) {
		return page > 1 ? page - 1 : 1;
	}

	/**
	 * 現在ページから次のページ数を出す
	 * @param page
	 * @return
	 */
	public int next(int page) {
		return page + 1;
	}

	/**
	 * 最大ページ数を出す
	 * @param page
	 * @param size
	 * @return
	 */
	public int last(int size) {
		return size / CustomerService.getPAGE_SIZE() + 1;
	}

}
