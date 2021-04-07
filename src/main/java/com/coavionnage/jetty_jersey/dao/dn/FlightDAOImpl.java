package com.coavionnage.jetty_jersey.dao.dn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightDAO;

public class FlightDAOImpl implements FlightDAO {

	private PersistenceManagerFactory pmf;

	public FlightDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getFlights(String departureAirfield) {
		// TODO Auto-generated method stub
		List<Flight> actions = null;
		List<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);

			if (departureAirfield != null) {
				q.declareParameters("String departure");
				q.setFilter("departureAirfield == departure");
				// Variable 'departure' is unbound and cannot be determined
				actions = (List<Flight>) q.execute(departureAirfield);

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

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Booking> getBookings() { List<Booking> book = null;
	 * PersistenceManager pm = pmf.getPersistenceManager(); Transaction tx =
	 * pm.currentTransaction(); try { tx.begin(); Query q =
	 * pm.newQuery(Flight.class); book = (List<Booking>) q.execute(); tx.commit(); }
	 * finally { if (tx.isActive()) { tx.rollback(); } pm.close(); } return book; }
	 */

	@Override
	public List<Booking> getBookings(String bookID) {
		return DAO.getBookingDAO().getBookings(bookID);
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
	public void editFlight(Flight flight) throws ParseException {
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
					fl.setDepartureTime(new SimpleDateFormat("dd-MM-yyy hh:mm:ss").format(flight.getDepartureTime()));
					fl.setArrivalTime(new SimpleDateFormat("dd-MM-yyy hh:mm:ss").format(flight.getArrivalTime()));
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
