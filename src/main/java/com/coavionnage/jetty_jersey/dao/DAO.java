package com.coavionnage.jetty_jersey.dao;

import com.coavionnage.jetty_jersey.dao.fake.BookingDAOFakeImpl;
import com.coavionnage.jetty_jersey.dao.fake.FlightDAOFakeImpl;
import com.coavionnage.jetty_jersey.dao.fake.UserDAOFakeImpl;

public class DAO {

	public static UserDAO getUserDAO() {
		return new UserDAOFakeImpl();
	}

	public static BookingDAO getBookingDAO() {
		return new BookingDAOFakeImpl();
	}

	public static FlightDAO getFlightDAO() {
		return new FlightDAOFakeImpl();
	}

}