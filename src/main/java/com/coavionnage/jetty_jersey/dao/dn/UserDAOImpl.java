package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAO;
import com.example.datanucleus.dao.Action;

public class UserDAOImpl implements UserDAO {

	private PersistenceManagerFactory pmf;

	public UserDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String uid) {
		// TODO Auto-generated method stub
		List<User> actions = null;
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Action.class);
			if (uid != null) {
				q.declareParameters("String user");
				q.setFilter("username == user");
				actions = (List<User>) q.execute(uid);
				detached = (List<User>) pm.detachCopyAll(actions);
			}

			else {
				actions = (List<User>) q.execute();
				detached = (List<User>) pm.detachCopyAll(actions);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Pilot> getPilots(String pname) {
		// TODO Auto-generated method stub
		List<Pilot> actions = null;
		List<Pilot> detached = new ArrayList<Pilot>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);
			if (pname != null) {
				q.declareParameters("String user");
				q.setFilter("username == user");

				actions = (List<Pilot>) q.execute(pname);
				detached = (List<Pilot>) pm.detachCopyAll(actions);
			} else {
				actions = (List<Pilot>) q.execute();
				detached = (List<Pilot>) pm.detachCopyAll(actions);
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
	public void addUser(User user) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(user);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public void editUser(User user) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Extent<User> e = pm.getExtent(User.class, true);
			for (User u : e) {
				if (u.getUserID().equals(user.getUserID())) {
					u.setEmail(user.getEmail());
					u.setName(user.getName());
					u.setPassword(user.getPassword());
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

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.deletePersistent(u);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}
