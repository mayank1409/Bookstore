package com.bookstore.entity;
// Generated 03-Sep-2021, 4:45:16 PM by Hibernate Tools 4.3.5.Final

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -711704530409170005L;
	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	public Users() {
	}

	public Users(Integer userId, String email, String password, String fullName) {
		this(email, password, fullName);
		this.userId = userId;
	}

	public Users(String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public Users(int id, String email, String fullName) {
		this.userId = id;
		this.email = email;
		this.fullName = fullName;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}