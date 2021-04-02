package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface FlightDAO {

	/**
	 * @return the list of flights
	 */
	List<Flight> getFlights(String departure);

	/**
	 * Add a new flight to the database
	 * 
	 * @param flight
	 * @return
	 */
	Flight addFlight(Flight flight);

	int getBookingNumber();

	void deleteFlight(Flight flight);

	void editFlight(Flight flight);

	List<Booking> getBookings();

}
