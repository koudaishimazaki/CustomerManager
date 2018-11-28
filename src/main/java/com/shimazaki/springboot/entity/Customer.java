package com.shimazaki.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(name = "first_name", length = 30, nullable = false)
	private String first_name;

	@Column(name = "last_name", length = 30, nullable = false)
	private String last_name;

	@Column(name = "f_name_kana", length = 30, nullable = false)
	private String f_name_kana;

	@Column(name = "l_name_kana", length = 30, nullable = false)
	private String l_name_kana;

	@Column(name = "tell", length = 20, nullable = true)
	private String tell;

	@Column(name = "mail", length = 256, nullable = true)
	private String mail;

	@Column(name = "postal_code", length = 7, nullable = true)
	private String postal_code;

	@Column(name = "state", length = 10, nullable = false)
	private String state;

	@Column(name = "city", length = 150, nullable = false)
	private String city;

	@Column(name = "address", length = 256, nullable = false)
	private String address;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;

	@Column(name = "deleted")
	private Date deleted;

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
	 * @return first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name セットする first_name
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name セットする last_name
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return f_name_kana
	 */
	public String getF_name_kana() {
		return f_name_kana;
	}

	/**
	 * @param f_name_kana セットする f_name_kana
	 */
	public void setF_name_kana(String f_name_kana) {
		this.f_name_kana = f_name_kana;
	}

	/**
	 * @return l_name_kana
	 */
	public String getL_name_kana() {
		return l_name_kana;
	}

	/**
	 * @param l_name_kana セットする l_name_kana
	 */
	public void setL_name_kana(String l_name_kana) {
		this.l_name_kana = l_name_kana;
	}

	/**
	 * @return tell
	 */
	public String getTell() {
		return tell;
	}

	/**
	 * @param tell セットする tell
	 */
	public void setTell(String tell) {
		this.tell = tell;
	}

	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail セットする mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address セットする address
	 */
	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * @return deleted
	 */
	public Date getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted セットする deleted
	 */
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

}
