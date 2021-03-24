package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

	@PrimaryKey
	private String userID;
	private String name;
	private String email;
	private String password;

	public User() {
		super();
	}

	public User(String id, String name, String email, String password) {
		this.userID = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String uid) {
		this.userID = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
