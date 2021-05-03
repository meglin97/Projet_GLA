package com.coavionnage.jetty_jersey.dao;

import java.util.Date;
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

	boolean editFlight(Integer id, String arrival, Date dep, Date arr, int nb);

	List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, Date date);

	int flightNumber();

	Flight getFlight(Integer fid);

	int flightPlaces(Integer fid);

}
