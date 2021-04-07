package com.coavionnage.jetty_jersey.dao.fake;

import java.util.List;

import com.coavionnage.jetty_jersey.dao.Booking;
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
	public void deleteFlight(Flight flight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editFlight(Flight flight) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Booking> getBookings(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
