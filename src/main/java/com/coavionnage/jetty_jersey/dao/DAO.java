package com.coavionnage.jetty_jersey.dao;

public class DAO {
	public FlightBookingDAO getFlightBookingDAO() {
		return new FlightBookingDAOImpl();
	}

	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

}