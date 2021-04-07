package com.coavionnage.jetty_jersey.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Flight {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	public Integer flightID;
	
	@Persistent
	public String departureAirfield;
	
	@Persistent
	public String arrivalAirfield;
	
	@Persistent
	public LocalDateTime departureTime;
	
	@Persistent
	public LocalDateTime arrivalTime;
	
	@Persistent
	public List<Booking> bookList;
	
	@Persistent
	private int numberPlaces;
	
	@Persistent
	private Pilot pilot;

	public Flight() {

	}

	public Flight(Integer flightID, String departure, String arrival, LocalDateTime depTime, LocalDateTime arrTime,
			int numberPlaces, Pilot p) {
		this.flightID = flightID;
		this.departureAirfield = departure;
		this.arrivalAirfield = arrival;
		this.departureTime = depTime;
		this.arrivalTime = arrTime;
		this.bookList = new ArrayList<Booking>();
		this.numberPlaces = numberPlaces;
		this.pilot = p;

	}

	public Integer getFlightID() {
		return flightID;
	}

	public void setFlightID(Integer fid) {
		this.flightID = fid;
	}

	public String getDepartureAirfield() {
		return departureAirfield;
	}

	public void setDepartureAirfield(String departureAirfield) {
		this.departureAirfield = departureAirfield;
	}

	public String getArrivalAirfield() {
		return arrivalAirfield;
	}

	public void setArrivalAirfield(String arrivalAirfield) {
		this.arrivalAirfield = arrivalAirfield;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getNumberPlaces() {
		return numberPlaces;
	}

	public void setNumberPlaces(int numberPlaces) {
		this.numberPlaces = numberPlaces;
	}

	public Pilot getpilot() {
		return pilot;
	}

	public void setPilot(Pilot p) {
		this.pilot = p;
	}

	public List<Booking> getBookings() {
		return bookList;
	}

	public void setBooking(Booking book) {
		this.bookList.add(book);
	}

	public int getBookingNumber() {
		return this.bookList.size();
	}
}
