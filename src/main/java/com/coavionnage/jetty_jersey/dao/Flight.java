package com.coavionnage.jetty_jersey.dao;

import java.text.ParseException;
import java.util.Date;

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
	public Date departureTime;
	@Persistent

	public Date arrivalTime;

	private int numberPlaces;

	@Persistent
	private int pilot;

	private float ticketPrice;

	public Flight() {
		super();
	}

	public Flight(Integer flightID, String departure, String arrival, Date depTime, Date arrTime, int numberPlaces,
			int pilotID, float ticketPrice) throws ParseException {

		this.flightID = flightID;
		this.departureAirfield = departure;
		this.arrivalAirfield = arrival;
		this.departureTime = depTime;
		this.arrivalTime = arrTime;
		this.numberPlaces = numberPlaces;
		this.pilot = pilotID;
		this.setTicketPrice(ticketPrice);

	}

	public Integer getFlightID() {
		return flightID;
	}

	public void setFlightID(Integer string) {
		this.flightID = string;
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

	public void setDepartureTime(Date departureTime) throws ParseException {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) throws ParseException {
		this.arrivalTime = arrivalTime;
	}

	public int getNumberPlaces() {
		return numberPlaces;
	}

	public void setNumberPlaces(int numberPlaces) {
		this.numberPlaces = numberPlaces;
	}

	public int getpilot() {
		return pilot;
	}

	public void setPilot(int p) {
		this.pilot = p;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
