package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable
public class Booking {

	// public final static String BOOKING_STATUS_PENDING_RESPONSE =
	// "pending_response";
	// public final static String BOOKING_STATUS_ACCEPTED = "accepted";
	// public final static String BOOKING_STATUS_REJECTED = "rejected";

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	@Unique
	private Integer bookID;
	@Persistent
	private Integer flightID;
	@Persistent
	private Integer userID;

	private String status;

	public Booking() {
		super();
	}

	public Booking(Integer bid, Integer fid, Integer u, String status) {
		this.bookID = bid;
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
}
