
package com.coavionnage.jetty_jersey.dao;

import java.util.List;
import java.util.Optional;

public interface FlightBookingDAO {

	public static Optional<Passenger> getPassenger(String uid) {
		return null;
	}

	public static Optional<Flight> getFlight(String bid) {
		return null;
	}

	public static Optional<FlightBooking> getBooking(String bid) {
		return null;
	}

	public static List<Passenger> getPassengers() {
		return null;
	}

	public static List<Flight> getAllFlight() {
		return null;
	}

	public static List<FlightBooking> getAllBooking() {
		return null;
	}
}
