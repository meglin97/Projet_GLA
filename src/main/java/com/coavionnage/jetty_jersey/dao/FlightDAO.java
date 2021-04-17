package com.coavionnage.jetty_jersey.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface FlightDAO {

	/**
	 * @return the list of flights
	 */
	List<Flight> getFlights();

	List<Flight> getFlights(String departure, String arrival, Date departureDate, Date arrivaldate);

	/**
	 * Add a new flight to the database
	 * 
	 * @param flight
	 * @return
	 */
	Flight addFlight(Flight flight);

	boolean deleteFlight(Integer flightID);

	Flight editFlight(Flight flight) throws ParseException;

}
