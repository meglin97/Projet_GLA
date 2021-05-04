package com.coavionnage.jetty_jersey.dao.fake;

import java.util.List;

import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightDAO;

public class FlightDAOFakeImpl implements FlightDAO {
	@Override
	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight addFlight(Flight flight) {
		return flight;
		// TODO Auto-generated method stub
	}

	@Override
	public List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, String departureDate,
			String arrivalDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int flightNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Flight getFlight(Integer fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int flightPlaces(Integer fid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteFlight(Integer flightID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editFlight(Flight flight) {
		// TODO Auto-generated method stub
		return false;
	}

}
