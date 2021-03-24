package com.coavionnage.jetty_jersey.dao;

import java.time.LocalDateTime;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Flight {
	public String flightID;
	public String departureAirfield;
	public String arrivalAirfield;
	public LocalDateTime departureTime;
	public LocalDateTime arrivalTime;

	private int numberPlaces;
	private Pilot pilot;

	public Flight() {

	}

	public Flight(String id, String departure, String arrival, LocalDateTime depTime, LocalDateTime arrTime,
			int numberPlaces, Pilot p) {
		this.flightID = id;
		this.departureAirfield = departure;
		this.arrivalAirfield = arrival;
		this.departureTime = depTime;
		this.arrivalTime = arrTime;

		this.numberPlaces = numberPlaces;
		this.pilot = p;

	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String fid) {
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

}
