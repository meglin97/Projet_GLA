package com.coavionnage.jetty_jersey.dao.dn;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

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
		if (detached.size() == 0)
			return null;
		return detached;
	}

	public List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, String date) {
		String[] dateArr = date.split("-");
		Date startDate = new Date(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),Integer.parseInt( dateArr[2]), 0, 0);
		Date endDate = new Date(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),Integer.parseInt( dateArr[2]), 23, 59);
		
		
		List<Flight> actions = null;
		List<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);

			if (departureAirfield != null) {
				q.declareParameters("String departure, String arrival, long startDate, long endDate");
				q.setFilter("departureAirfield.toLowerCase() == departure && arrivalAirfield.toLowerCase() == arrival && departureTime > startDate && departureTime < endDate");
				// Variable 'departure' is unbound and cannot be determined
				actions = (List<Flight>) q.executeWithArray(departureAirfield.toLowerCase(), arrivalAirfield.toLowerCase(), startDate.getTime(), endDate.getTime());
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

	@Override
	public boolean deleteFlight(String flightID) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean bool = false;
		try {
			tx.begin();
			Extent<Flight> e = pm.getExtent(Flight.class, true);
			Iterator<Flight> iter = e.iterator();
			while (iter.hasNext()) {
				Flight f = iter.next();
				if (f.getFlightID().equals(flightID)) {
					pm.deletePersistent(f);
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
	public Flight editFlight(Flight flight) throws ParseException {
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
		return flight;
	}
}
