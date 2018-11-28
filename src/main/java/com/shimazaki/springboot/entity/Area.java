package com.shimazaki.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(name  = "postal_code", length = 7)
	private String postal_code;

	@Column(name  = "state", length = 10)
	private String state;

	@Column(name  = "city", length = 150)
	private String city;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return postal_code
	 */
	public String getPostal_code() {
		return postal_code;
	}

	/**
	 * @param postal_code セットする postal_code
	 */
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state セットする state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city セットする city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created セットする created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated セットする updated
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
