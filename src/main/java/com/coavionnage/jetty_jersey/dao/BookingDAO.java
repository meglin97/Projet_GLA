
package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface BookingDAO {

	List<Booking> getBookings();

	Booking addBooking(Booking booking);

	boolean deleteBooking(Integer bookID);

	Booking editBooking(Booking booking);

	int bookingNumber(Integer flightID);

	List<Booking> addBookings(int nbBookings, Booking booking);

	int userBookings(Integer uid);

	int totalBooking();

	Booking getBooking(Integer bid);

}
