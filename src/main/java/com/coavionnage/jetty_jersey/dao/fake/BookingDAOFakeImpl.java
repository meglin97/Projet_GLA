package com.coavionnage.jetty_jersey.dao.fake;

import java.util.List;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.BookingDAO;

public class BookingDAOFakeImpl implements BookingDAO {

	@Override
	public List<Booking> getBookings() {
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

	@Override
	public int bookingNumber(Integer flightID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Booking> addBookings(int nbBookings, Booking booking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int userBookings(Integer uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalBooking() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Booking getBooking(Integer bid) {
		// TODO Auto-generated method stub
		return null;
	}

}
