package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.NotFoundException;

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
	public List<Pilot> getPilots(Integer userID) {

		List<Pilot> pilots = null;
		List<Pilot> detached = new ArrayList<Pilot>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Pilot.class);
			if (userID != null) {
				q.declareParameters("Integer user");
				q.setFilter("userID == user");

				pilots = (List<Pilot>) q.execute(userID);
				detached = (List<Pilot>) pm.detachCopyAll(pilots);
			} else {
				pilots = (List<Pilot>) q.execute();
				detached = (List<Pilot>) pm.detachCopyAll(pilots);
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
	public Pilot addPilot(User u, int nbHours, int nbYears, String qualifications) {
		// TODO Auto-generated method stub

		Pilot pilot = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.setRetainValues(true);
		try {
			tx.begin();
			pilot = new Pilot(u.getUserID(), u.getFirstName(), u.getLastName(), u.getEmail(), nbHours, nbYears,
					qualifications);
			pm.makePersistent(pilot);
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
	public boolean deleteUser(Integer userID) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		boolean bool = false;
		try {
			tx.begin();
			Pilot pilot = this.getPilots(userID).get(0);
			pm.deletePersistent(pilot);
			tx.commit();
		} catch (Exception e) {
			throw new NotFoundException();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return bool;
	}

	@Override
	public Pilot editPilot(User u, int nbHours, int nbYears, String qualifications) {
		Pilot pilot = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pilot = this.getPilots(u.getUserID()).get(0);
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

}
