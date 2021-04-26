package com.coavionnage.jetty_jersey.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

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
	public LocalDateTime departureDate;

	@Persistent
	public LocalDateTime arrivalDate;
	@Persistent
	private int numberPlaces;
	@Persistent
	@Unique
	private Integer pilot;
	@Persistent
	private float ticketPrice;

	public Flight() {
		super();
	}

	public Flight(Integer flightID, String departure, String arrival, String depTime, String arrTime, int numberPlaces,
			Integer pilotID, float ticketPrice) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.flightID = flightID;
		this.departureAirfield = departure;
		this.arrivalAirfield = arrival;
		this.departureDate = LocalDateTime.parse(depTime, formatter);
		this.arrivalDate = LocalDateTime.parse(depTime, formatter);
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

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureTime) {
		this.departureDate = departureTime;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalTime) {
		this.arrivalDate = arrivalTime;
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

}
