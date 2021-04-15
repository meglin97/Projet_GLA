
package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface BookingDAO {

	List<Booking> getBookings(String bid);

	Booking addBooking(Booking booking);

	boolean deleteBooking(String bookID);

	Booking editBooking(Booking booking);

}
