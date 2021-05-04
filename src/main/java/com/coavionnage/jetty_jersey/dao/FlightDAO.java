package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface FlightDAO {

	/**
	 * @return the list of flights
	 */
	List<Flight> getFlights();

	/**
	 * Add a new flight to the database
	 * 
	 * @param flight
	 * @return
	 */
	Flight addFlight(Flight flight);

	boolean deleteFlight(Integer flightID);

	boolean editFlight(Flight flight);

	List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, String departureDate);

	int flightNumber();

	Flight getFlight(Integer fid);

	int flightPlaces(Integer fid);

}
