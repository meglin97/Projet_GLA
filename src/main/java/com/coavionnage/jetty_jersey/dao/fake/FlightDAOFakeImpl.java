package com.coavionnage.jetty_jersey.dao.fake;

import java.util.Date;
import java.util.List;

import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightDAO;

public class FlightDAOFakeImpl implements FlightDAO {

	@Override
	public List<Flight> getFlights(String departure) {

		return null;
	}

	@Override
	public Flight addFlight(Flight flight) {
		return flight;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean deleteFlight(String flight) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public Flight editFlight(Flight flight) {
		return flight;
		// TODO Auto-generated method stub

	}

	@Override
	public List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
