package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable
public class User {

	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private Integer userID;

	@Persistent
	private String name;
	@PrimaryKey
	@Persistent
	@Unique
	private String email;

	@Persistent
	private String password;

	public User() {
		super();
	}

	public User(Integer userID, String name, String email, String password) {
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer uid) {
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

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
