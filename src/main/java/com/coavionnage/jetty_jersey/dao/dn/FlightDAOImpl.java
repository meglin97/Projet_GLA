package com.coavionnage.jetty_jersey.dao.dn;

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
	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		List<Flight> actions = null;
		List<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);
			actions = (List<Flight>) q.execute();
			detached = (List<Flight>) pm.detachCopyAll(actions);
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

	@Override
	public Flight getFlight(Integer fid) {
		for (Flight f : this.getFlights()) {
			if (f.getFlightID().equals(fid)) {
				return f;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, Date date) {
		// TODO Auto-generated method stub
		List<Flight> actions = null;
		List<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String departure, String arrival,Date d");
			q.setFilter("departureAirfield==departure && arrivalAirfield==arrival && date==d");
			actions = (List<Flight>) q.execute(departureAirfield, arrivalAirfield, date);
			detached = (List<Flight>) pm.detachCopyAll(actions);
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
	public boolean deleteFlight(Integer flightID) {
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
	public Flight editFlight(Flight flight) {
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
					try {
						fl.setDepartureDate(flight.getDepartureDate());
						fl.setArrivalDate(flight.getArrivalDate());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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

	@Override
	public int flightNumber() {
		return this.getFlights().size();
	}

	@Override
	public int flightPlaces(Integer fid) {
		return this.getFlight(fid).getNumberPlaces();
	}
}
