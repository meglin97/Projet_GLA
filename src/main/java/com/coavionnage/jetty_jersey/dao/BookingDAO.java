
package com.coavionage.jetty_jersey.dao;

import java.util.List;

public interface BookingDAO {

	List<Booking> getBookings(String bid);

	Booking addBooking(Booking booking);

	void deleteBooking(Booking booking);

	void editBooking(Booking booking);

}
