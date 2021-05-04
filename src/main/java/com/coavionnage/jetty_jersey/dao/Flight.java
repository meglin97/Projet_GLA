package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable
public class Flight {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	@Unique
	public Integer flightID;

	public String departureAirfield;

	public String arrivalAirfield;

	public String departureDate;

	public String arrivalDate;

	public String departureTime;

	public String arrivalTime;

	private int numberPlaces;

	@Unique
	private Integer pilot;

	private float ticketPrice;

	public Flight() {
		super();
	}

	public Flight(String departure, String arrival, String depDate, String arrDate, String depTime, String arrTime,
			int numberPlaces, Integer pilotID, float ticketPrice) {

		this.departureAirfield = departure;
		this.arrivalAirfield = arrival;

		this.departureDate = depDate;
		this.arrivalDate = arrDate;
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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureTime) {
		this.departureDate = departureTime;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalTime) {
		this.arrivalDate = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getNumberPlaces() {
		return numberPlaces;
	}

	public void setNumberPlaces(int numberPlaces) {
		this.numberPlaces = numberPlaces;
	}

	public Integer getpilot() {
		return pilot;
	}

	public void setPilot(Integer p) {
		this.pilot = p;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return this.departureAirfield + " ---> " + this.arrivalAirfield + " for " + this.departureDate + " "
				+ this.departureTime + " to " + this.arrivalDate + " " + this.arrivalTime;
	}
}
