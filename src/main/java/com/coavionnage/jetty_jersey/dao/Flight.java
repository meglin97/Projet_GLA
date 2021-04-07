package com.coavionnage.jetty_jersey.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
<<<<<<< HEAD
	public Date departureTime;
	public Date arrivalTime;
=======
	
	@Persistent
	public LocalDateTime departureTime;
	
	@Persistent
	public LocalDateTime arrivalTime;
	
	@Persistent
>>>>>>> 4ed12e0ab9b4d2bc07071b35d8a0088cbab96d93
	public List<Booking> bookList;
	
	@Persistent
	private int numberPlaces;
	
	@Persistent
	private Pilot pilot;

	public Flight() {

	}

<<<<<<< HEAD
	public Flight(String id, String departure, String arrival, Date depTime, Date arrTime, int numberPlaces, Pilot p) {
		this.flightID = id;
=======
	public Flight(Integer flightID, String departure, String arrival, LocalDateTime depTime, LocalDateTime arrTime,
			int numberPlaces, Pilot p) {
		this.flightID = flightID;
>>>>>>> 4ed12e0ab9b4d2bc07071b35d8a0088cbab96d93
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

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) throws ParseException {
		SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
		this.departureTime = parse.parse(departureTime);
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) throws ParseException {
		DateFormat parse = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
		this.arrivalTime = parse.parse(arrivalTime);
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

	public void setBooking(List<Booking> book) {
		this.bookList = book;
	}

	public int getBookingNumber() {
		return this.bookList.size();
	}

}
