package com.coavionage.jetty_jersey.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.coavionage.jetty_jersey.dao.dn.BookingDAOImpl;
import com.coavionage.jetty_jersey.dao.dn.FlightDAOImpl;
import com.coavionage.jetty_jersey.dao.dn.UserDAOImpl;

public class DAO {

	private static UserDAO userDAO = null;
	private static FlightDAO flightDAO = null;
	private static BookingDAO bookingDAO = null;

	public static UserDAO getUserDAO() {
		if (userDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionage");
			userDAO = new UserDAOImpl(pmf);
		}
		return userDAO;
	}

	public static BookingDAO getBookingDAO() {
		if (bookingDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionage");
			bookingDAO = new BookingDAOImpl(pmf);
		}
		return bookingDAO;
	}

	public static FlightDAO getFlightDAO() {
		if (flightDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionage");
			flightDAO = new FlightDAOImpl(pmf);
		}
		return flightDAO;
	}

}