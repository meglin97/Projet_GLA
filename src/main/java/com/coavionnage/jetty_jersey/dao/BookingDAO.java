
package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface BookingDAO {

	List<Booking> getBookings(String bid);

	void addBooking(Booking booking);

}
