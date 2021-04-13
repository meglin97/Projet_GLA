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
	private String bookID;
	@Persistent
	private String flightID;
	@Persistent
	private int user;
	@Persistent
	private String placeNumber;

	private String status;
	public float ticketPrice;

	public Booking() {
		super();
	}

	public Booking(String bid, String fid, int u, String num, String status, float ticketPrice) {
		this.bookID = bid;
		this.flightID = fid;
		this.user = u;
		this.placeNumber = num;
		this.status = status;
		this.ticketPrice = ticketPrice;
	}

	public String getBookingID() {
		return bookID;
	}

	public void setBookingID(String bid) {
		this.bookID = bid;
	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String fid) {
		this.flightID = fid;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(String numPlace) {
		this.placeNumber = numPlace;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
