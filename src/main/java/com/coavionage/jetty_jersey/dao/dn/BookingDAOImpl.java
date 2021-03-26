package com.coavionage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coavionage.jetty_jersey.dao.Booking;
import com.coavionage.jetty_jersey.dao.BookingDAO;

public class BookingDAOImpl implements BookingDAO {

	private PersistenceManagerFactory pmf;

	public BookingDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	public List<Booking> getBookings(String bid) {
		List<Booking> books = null;
		List<Booking> detached = new ArrayList<Booking>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Booking.class);
			if (bid != null) {
				q.declareParameters("String bookID");
				q.setFilter("bid == bookID");
				books = (List<Booking>) q.execute(bid);
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
	public void addBooking(Booking booking) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(booking);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}