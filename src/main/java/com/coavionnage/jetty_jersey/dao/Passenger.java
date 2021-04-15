package com.coavionnage.jetty_jersey.dao;

public class Passenger extends User {

	private String placeNum;
	private String flightID;

	public Passenger() {
		// TODO Auto-generated constructor stub
	}

	public Passenger(String id, String name, String email, String password, String role, String flightID,
			String place) {
		super(id, name, email, password, role);

		this.flightID = flightID;
		this.placeNum = place;
	}

	public String getPlaceNum() {
		return this.placeNum;
	}

	public void setPlaceNum(String place) {
		this.placeNum = place;
	}

	public String getFlightID() {
		return this.flightID;
	}

	public void setFlightID(String fid) {
		this.flightID = fid;
	}
}
