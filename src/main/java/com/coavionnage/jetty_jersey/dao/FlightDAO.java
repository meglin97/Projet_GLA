package com.coavionnage.jetty_jersey.dao;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightDAO {

	/**
	 * @return the list of flights
	 */
	List<Flight> getFlights(String departure, String arrival, LocalDateTime departureDate);

	/**
	 * Add a new flight to the database
	 * 
	 * @param flight
	 */
	void addFlight(Flight flight);

}
