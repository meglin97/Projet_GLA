package com.coavionnage.jetty_jersey.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable
public class Booking {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	@Unique
	private Integer bookID;
	@Persistent
	private Integer flightID;
	@Persistent
	private Integer userID;
	@Persistent
	private String status;

	@Persistent
	private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm"));

	public Booking() {
		super();
	}

	public Booking(Integer fid, Integer u, String status) {
		this.flightID = fid;
		this.userID = u;
		this.status = status;

	}

	public Integer getBookingID() {
		return bookID;
	}

	public void setBookingID(Integer bid) {
		this.bookID = bid;
	}

	public Integer getFlightID() {
		return flightID;
	}

	public void setFlightID(Integer fid) {
		this.flightID = fid;
	}

	public Integer getUser() {
		return userID;
	}

	public void setUser(Integer userID) {
		this.userID = userID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return this.date;
	}

	@Override
	public String toString() {
		return "bookID : " + bookID + "\nflightID : " + flightID + "\nuserID : " + userID + "\nstatus : " + status
				+ "\ndate time : " + date;
	}
}
