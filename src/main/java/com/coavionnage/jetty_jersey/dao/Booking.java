package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Booking {

	// public final static String BOOKING_STATUS_PENDING_RESPONSE =
	// "pending_response";
	// public final static String BOOKING_STATUS_ACCEPTED = "accepted";
	// public final static String BOOKING_STATUS_REJECTED = "rejected";

	private String bookID;
	private String flightID;
	private User user;
	private String numPlace;
	private String status;
	public String pilotID;
	public float ticketPrice;

	public Booking() {
		super();
	}

	public Booking(String bid, String fid, User u, String num, String status, String pid, float ticketPrice) {
		super();
		this.bookID = bid;
		this.flightID = fid;
		this.user = u;
		this.numPlace = num;
		this.status = status;
		this.pilotID = pid;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPlaceNumber() {
		return numPlace;
	}

	public void setPlaceNumber(String numPlace) {
		this.numPlace = numPlace;
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
