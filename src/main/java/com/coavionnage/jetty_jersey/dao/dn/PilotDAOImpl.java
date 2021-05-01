package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.PilotDAO;
import com.coavionnage.jetty_jersey.dao.User;

public class PilotDAOImpl implements PilotDAO {

	private PersistenceManagerFactory pmf;

	public PilotDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pilot> getPilots() {
		List<Pilot> pilots = null;
		List<Pilot> detached = new ArrayList<Pilot>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Pilot.class);
			pilots = (List<Pilot>) q.execute();
			detached = (List<Pilot>) pm.detachCopyAll(pilots);
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
	public Pilot addPilot(User u, int nbHours, int nbYears, String qualifications) {
		Pilot pilot = new Pilot(u.getUserID(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword());
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pilot.setNumberOfHoursFlights(nbHours);
			pilot.setExperience(nbYears);
			pilot.setQualifications(qualifications);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return pilot;
	}

	@Override
	public void editPilot(User u, int nbHours, int nbYears, String qualifications) {
		Pilot pilot = (Pilot) u;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.deletePersistent(u);
			pilot.setNumberOfHoursFlights(nbHours);
			pilot.setExperience(nbYears);
			pilot.setQualifications(qualifications);
			pm.makePersistent(pilot);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

}
