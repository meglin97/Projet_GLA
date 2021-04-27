package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Booking> getBookings(String bookID) {
		List<Booking> books = null;
		List<Booking> detached = new ArrayList<Booking>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Booking.class);
			if (bookID != null) {
				q.declareParameters("String bid");
				q.setFilter("bookID == bid");
				books = (List<Booking>) q.execute(bookID);
				detached = (List<Booking>) pm.detachCopyAll(books);
			} else {
				books = (List<Booking>) q.execute();
				detached = (List<Booking>) pm.detachCopyAll(books);
			}

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
		List<Booking> books = new ArrayList<Booking>();
		for (int i = 0; i < nbBookings; i++) {
			books.add(addBooking(booking));
		}
		return books;
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
		return this.getBookings(null).size();
	}

	@Override
	public int bookingNumber(Integer flightID) {
		List<Booking> list = this.getBookings(null);
		return list.stream().filter(b -> b.getFlightID().equals(flightID)).collect(Collectors.toList()).size();
	}

	@Override
	public int userBookings(Integer userID) {
		List<Booking> list = this.getBookings(null);
		return list.stream().filter(b -> b.getUser().equals(userID)).collect(Collectors.toList()).size();
	}
}