package com.coavionnage.jetty_jersey.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.coavionnage.jetty_jersey.dao.dn.BookingDAOImpl;
import com.coavionnage.jetty_jersey.dao.dn.FlightDAOImpl;
import com.coavionnage.jetty_jersey.dao.dn.PilotDAOImpl;
import com.coavionnage.jetty_jersey.dao.dn.UserDAOImpl;

public class DAO {

	private static UserDAO userDAO = null;
	private static PilotDAO pilotDAO = null;
	private static FlightDAO flightDAO = null;
	private static BookingDAO bookingDAO = null;

	public static UserDAO getUserDAO() {
		if (userDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionnage");
			userDAO = new UserDAOImpl(pmf);
		}
		return userDAO;
	}

	public static BookingDAO getBookingDAO() {
		if (bookingDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionnage");
			bookingDAO = new BookingDAOImpl(pmf);
		}
		return bookingDAO;
	}

	public static FlightDAO getFlightDAO() {
		if (flightDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionnage");
			flightDAO = new FlightDAOImpl(pmf);
		}
		return flightDAO;
	}

	public static PilotDAO getPilotDAO() {
		if (pilotDAO == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionnage");
			pilotDAO = new PilotDAOImpl(pmf);
		}
		return pilotDAO;
	}
}