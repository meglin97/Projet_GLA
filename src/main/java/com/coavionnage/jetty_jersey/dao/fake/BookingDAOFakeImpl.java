package com.coavionnage.jetty_jersey.dao.fake;

import java.util.List;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.BookingDAO;

public class BookingDAOFakeImpl implements BookingDAO {

	@Override
	public List<Booking> getBookings(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub

		return null;

	}

	@Override
	public boolean deleteBooking(Integer booking) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public Booking editBooking(Booking booking) {
		return booking;
		// TODO Auto-generated method stub

	}

}
