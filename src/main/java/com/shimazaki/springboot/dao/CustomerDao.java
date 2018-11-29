package com.shimazaki.springboot.dao;

import java.io.Serializable;
import java.util.List;

public interface CustomerDao<T> extends Serializable {

	public List<T> getAll();

}

