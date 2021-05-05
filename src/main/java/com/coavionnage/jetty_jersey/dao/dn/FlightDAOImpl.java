package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
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

	@Override
	public List<Flight> searchByCriteria(String departureAirfield, String arrivalAirfield, String departureDate) {
		// TODO Auto-generated method stub
		List<Flight> flights = new ArrayList<Flight>();
		for (Flight f : this.getFlights()) {
			if (f.getDepartureAirfield().equals(departureAirfield) && f.getArrivalAirfield().equals(arrivalAirfield)
					&& f.getDepartureDate().equals(departureDate)) {
				flights.add(f);
			}
		}

		return flights;
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
	public boolean editFlight(Flight flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean bool = false;
		try {
			tx.begin();

			Extent<Flight> e = pm.getExtent(Flight.class, true);
			Iterator<Flight> iter = e.iterator();
			while (iter.hasNext()) {
				Flight f = iter.next();
				if (f.getFlightID().equals(flight.getFlightID())) {
					f.setDepartureAirfield(flight.getDepartureAirfield());
					f.setArrivalAirfield(flight.getArrivalAirfield());

					f.setDepartureDate(flight.getDepartureDate());
					f.setArrivalDate(flight.getArrivalDate());
					f.setDepartureTime(flight.getDepartureTime());

					f.setArrivalTime(flight.getArrivalTime());

					f.setNumberPlaces(flight.getNumberPlaces());

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
	public int flightNumber() {
		return this.getFlights().size();
	}

	@Override
	public int flightPlaces(Integer fid) {
		return this.getFlight(fid).getNumberPlaces();
	}

}
