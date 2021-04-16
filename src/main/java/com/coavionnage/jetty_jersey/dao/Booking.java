package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Booking {

	// public final static String BOOKING_STATUS_PENDING_RESPONSE =
	// "pending_response";
	// public final static String BOOKING_STATUS_ACCEPTED = "accepted";
	// public final static String BOOKING_STATUS_REJECTED = "rejected";

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private Integer bookID;
	@Persistent
	private String flightID;
	@Persistent
	private int userID;

	private String status;

	public Booking() {
		super();
	}

	public Booking(Integer bid, String fid, int u, String status) {
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

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String fid) {
		this.flightID = fid;
	}

	public int getUser() {
		return userID;
	}

	public void setUser(int user) {
		this.userID = user;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
