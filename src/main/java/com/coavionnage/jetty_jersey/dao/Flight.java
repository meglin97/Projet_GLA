package com.coavionnage.jetty_jersey.dao;

import java.text.ParseException;
import java.util.Date;

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
	public Date departureDate;
	@Persistent
	public Date arrivalDate;
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

	public Flight(Integer flightID, String departure, String arrival, Date depTime, Date arrTime, int numberPlaces,
			Integer pilotID, float ticketPrice) throws ParseException {

		this.flightID = flightID;
		this.departureAirfield = departure;
		this.arrivalAirfield = arrival;
		this.departureDate = depTime;
		this.arrivalDate = arrTime;
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

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureTime) throws ParseException {
		this.departureDate = departureTime;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalTime) throws ParseException {
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
