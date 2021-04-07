package com.coavionnage.jetty_jersey.dao;

import java.text.ParseException;
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

	void deleteFlight(Flight flight);

	void editFlight(Flight flight) throws ParseException;

	List<Booking> getBookings(String bid);

}
