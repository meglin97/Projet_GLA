package com.coavionage.jetty_jersey.dao.fake;

import java.util.List;

import com.coavionage.jetty_jersey.dao.Booking;
import com.coavionage.jetty_jersey.dao.BookingDAO;

public class BookingDAOFakeImpl implements BookingDAO {

	@Override
	public List<Booking> getBookings(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		Booking book = new Booking();
		book.setBookingID(booking.getBookingID());
		book.setFlightID(booking.getFlightID());
		book.setPlaceNumber(booking.getPlaceNumber());
		book.setUser(booking.getUser());
		book.setTicketPrice(booking.getTicketPrice());
		book.setStatus(booking.getStatus());
		getBookings(null).add(book);
		return booking;

	}

	@Override
	public void deleteBooking(Booking booking) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editBooking(Booking booking) {
		// TODO Auto-generated method stub

	}

}
