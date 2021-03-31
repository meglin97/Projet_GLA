package com.coavionnage.jetty_jersey.dao.fake;

import java.time.LocalDateTime;
import java.util.List;

import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightDAO;

public class FlightDAOFakeImpl implements FlightDAO {

	@Override
	public List<Flight> getFlights(String departure, String arrival, LocalDateTime departureDate) {

		return null;
	}

	@Override
	public Flight addFlight(Flight flight) {
		return flight;
		// TODO Auto-generated method stub
	}

	@Override
	public int getBookingNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteFlight(Flight flight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editFlight(Flight flight) {
		// TODO Auto-generated method stub

	}

}
