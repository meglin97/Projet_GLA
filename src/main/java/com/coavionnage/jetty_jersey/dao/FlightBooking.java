package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightBooking {

	// public final static String BOOKING_STATUS_PENDING_RESPONSE =
	// "pending_response";
	// public final static String BOOKING_STATUS_ACCEPTED = "accepted";
	// public final static String BOOKING_STATUS_REJECTED = "rejected";

	private String bookID;
	private String flightID;
	// private List<Passenger> passenger;
	private int numberPlaces;
	private String status;
	public float ticketPrice;
	public String pilotID;

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

	// public List<Passenger> getPassenger() {return passenger;}

	// public void setPassenger(Passenger passenger)
	// {this.passenger.add(passenger);}

	public int getNumberPlaces() {
		return numberPlaces;
	}

	public void setNumberPlaces(int numberPlaces) {
		this.numberPlaces = numberPlaces;
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

	public String getPilot() {
		return pilotID;
	}

	public void setPilot(String pid) {
		this.pilotID = pid;
	}

}
