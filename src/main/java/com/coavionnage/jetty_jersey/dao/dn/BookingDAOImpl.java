package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.BookingDAO;

public class BookingDAOImpl implements BookingDAO {

	private PersistenceManagerFactory pmf;

	public BookingDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Booking> getBookings() {
		List<Booking> books = null;
		List<Booking> detached = new ArrayList<Booking>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Booking.class);
			books = (List<Booking>) q.execute();
			detached = (List<Booking>) pm.detachCopyAll(books);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	@Override
	public Booking getBooking(Integer bid) {
		for (Booking b : this.getBookings()) {
			if (b.getBookingID().equals(bid)) {
				return b;
			}
		}
		return null;
	}

	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(booking);
			tx.commit();

			pm.flush();
			pm.refresh(booking);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return booking;
	}

	@Override
	public List<Booking> addBookings(int nbBookings, Booking booking) {
		// TODO Auto-generated method stub
		List<Booking> bookings = new ArrayList<Booking>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		for (int i = 0; i < nbBookings; i++) {
			bookings.add(booking);
		}
		try {
			tx.begin();
			pm.makePersistentAll(bookings);
			tx.commit();
			pm.flush();
			pm.refresh(booking);

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return bookings;
	}

	@Override
	public boolean deleteBooking(Integer bookID) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean bool = false;
		try {
			tx.begin();
			Extent<Booking> e = pm.getExtent(Booking.class, true);
			Iterator<Booking> iter = e.iterator();
			while (iter.hasNext()) {
				Booking book = iter.next();
				if (book.getBookingID().equals(bookID)) {
					pm.deletePersistent(book);
					bool = true;
				}
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return bool;
	}

	@Override
	public Booking editBooking(Booking booking) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Extent<Booking> e = pm.getExtent(Booking.class, true);
			Iterator<Booking> iter = e.iterator();
			while (iter.hasNext()) {
				Booking book = iter.next();
				if (book.getBookingID().equals(booking.getBookingID())) {
					book.setStatus(booking.getStatus());
				}
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return booking;
	}

	@Override
	public int totalBooking() {
		return this.getBookings().size();
	}

	@Override
	// number of booking for a flight
	public int bookingNumber(Integer flightID) {
		int nb = 0;
		for (Booking b : this.getBookings()) {
			if (b.getFlightID().equals(flightID)) {
				nb++;
			}
		}
		return nb;
	}

	@Override
	// number of booking the user bought
	public int userBookings(Integer userID) {
		int nb = 0;
		for (Booking b : this.getBookings()) {
			if (b.getUser().equals(userID)) {
				nb++;
			}
		}
		return nb;
	}
}