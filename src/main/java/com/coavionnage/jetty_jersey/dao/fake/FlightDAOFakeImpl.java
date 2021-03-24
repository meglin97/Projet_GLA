package com.coavionnage.jetty_jersey.dao.fake;

import java.time.LocalDateTime;
import java.util.List;

import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightDAO;

public class FlightDAOFakeImpl implements FlightDAO {

	@Override
	public List<Flight> getFlights(String departure, String arrival, LocalDateTime departureDate) {
		// TODO Auto-generated method stub
		LocalDateTime dateStart = LocalDateTime.of(departureDate.getYear(), departureDate.getMonth().getValue(),
				departureDate.getDayOfMonth(), 0, 0);
		LocalDateTime dateEnd = LocalDateTime.of(departureDate.getYear(), departureDate.getMonth().getValue(),
				departureDate.getDayOfMonth(), 23, 59);

		return null;
	}

	@Override
	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub

		if (getFlights(flight.getFlightID(), null, null) != null) {
			System.out.println("UserID already used");
			System.exit(0);
		}
		Flight f = new Flight();
		f.setFlightID(flight.getFlightID());
		f.setDepartureAirfield(flight.getDepartureAirfield());
		f.setArrivalAirfield(flight.getArrivalAirfield());
		f.setDepartureTime(flight.getDepartureTime());
		f.setArrivalTime(flight.getArrivalTime());
		f.setPilot(flight.getpilot());
		f.setNumberPlaces(flight.getNumberPlaces());
		getFlights(null, null, null).add(f);
	}

}
