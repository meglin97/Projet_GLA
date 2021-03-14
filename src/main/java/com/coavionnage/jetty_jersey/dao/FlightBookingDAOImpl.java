package com.coavionnage.jetty_jersey.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightBookingDAOImpl implements FlightBookingDAO {

	private static List<FlightBooking> bookings = new ArrayList<FlightBooking>();
	private static List<Flight> flights = new ArrayList<Flight>();
	private static List<User> users = new ArrayList<User>();

	static {

		flights.add(new Flight("1", "Paris", "Lille", LocalDateTime.of(2021, 02, 16, 16, 0, 0),
				LocalDateTime.of(2021, 02, 16, 17, 0, 0)));
		flights.add(new Flight("2", "Paris", "Lyon", LocalDateTime.of(2021, 02, 16, 16, 0, 0),
				LocalDateTime.of(2021, 02, 16, 17, 0, 0)));

	}

	public static List<FlightBooking> getAllBooking() {
		return bookings;
	}

	public static List<Flight> getAllFlight() {
		return flights;
	}

	public static List<User> getAllUser() {
		return users;
	}

	public static Optional<FlightBooking> getBooking(String bid) {
		return FlightBookingDAOImpl.getAllBooking().stream().filter(b -> b.getBookingID().equals(bid)).findFirst();
	}

	public static Optional<Flight> getFlight(String bid) {
		return FlightBookingDAOImpl.getAllFlight().stream().filter(f -> f.getFlightID().equals(bid)).findFirst();
	}

}