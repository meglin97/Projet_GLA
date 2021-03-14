package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

	@PrimaryKey
	public String userID;
	public String name;
	public String email;
	public String password;
	private String role;

	public User() {

	}

	public User(String id, String name, String email, String password, String r) {
		this.userID = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = r;
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

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
