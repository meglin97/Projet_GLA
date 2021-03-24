package com.coavionnage.jetty_jersey.dao.dn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	public List<Flight> getFlights(String departure, String arrival, LocalDateTime departureDate) {
		// TODO Auto-generated method stub
		List<Flight> actions = null;
		List<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);
			if (departure != null) {
				q.declareParameters("String depatureAirfield");
				q.setFilter("departure == depatureAirfield");
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
	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}
