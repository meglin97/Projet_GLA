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
import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightDAO;

public class FlightDAOImpl implements FlightDAO {

	private PersistenceManagerFactory pmf;

	public FlightDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getFlights(String departure) {
		// TODO Auto-generated method stub
		List<Flight> actions = null;
		List<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);
			if (departure != null) {
				q.declareParameters("String departure");
				q.setFilter("departure == departure");
				actions = (List<Flight>) q.execute(departure);
				detached = (List<Flight>) pm.detachCopyAll(actions);
			} else {
				actions = (List<Flight>) q.execute();
				detached = (List<Flight>) pm.detachCopyAll(actions);
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
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(flight);

			tx.commit();
			pm.flush();
			pm.refresh(flight);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return flight;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookings() {
		List<Booking> book = null;
		List<Booking> detached = new ArrayList<Booking>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("List<Booking> getBookings");
			book = (List<Booking>) q.execute();
			detached = (List<Booking>) pm.detachCopy(book);
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
	public int getBookingNumber() {
		// TODO Auto-generated method stub
		int bookNumber = 0;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			bookNumber = getBookings().size();
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return bookNumber;
	}

	@Override
	public void deleteFlight(Flight flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.deletePersistent(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public void editFlight(Flight flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Extent<Flight> e = pm.getExtent(Flight.class, true);
			Iterator<Flight> iter = e.iterator();
			while (iter.hasNext()) {
				Flight fl = iter.next();
				if (fl.getFlightID().equals(flight.getFlightID())) {
					fl.setArrivalAirfield(flight.getArrivalAirfield());
					fl.setDepartureTime(flight.getDepartureTime());
					fl.setArrivalTime(flight.getArrivalTime());
					fl.setNumberPlaces(flight.getNumberPlaces());
				}
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}
