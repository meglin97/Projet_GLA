package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.NotFoundException;

import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	private PersistenceManagerFactory pmf;

	public UserDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(Integer userID) {
		List<User> actions = null;
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);
			if (userID != null) {
				q.declareParameters("Integer user");
				q.setFilter("userID == user");

				actions = (List<User>) q.execute(userID);
				detached = (List<User>) pm.detachCopyAll(actions);
			} else {
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
	public List<Pilot> getPilots(Integer userID) {

		List<Pilot> actions = null;
		List<Pilot> detached = new ArrayList<Pilot>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Pilot.class);
			if (userID != null) {
				q.declareParameters("Integer user");
				q.setFilter("userID == user");

				actions = (List<Pilot>) q.execute(userID);
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
	public User addUser(User user) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(user);
			tx.commit();

			pm.flush();
			pm.refresh(user);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return user;
	}

	@Override
	public boolean deleteUser(int userID) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean bool = false;
		try {
			tx.begin();
			Extent<User> e = pm.getExtent(User.class, true);
			Iterator<User> iter = e.iterator();
			while (iter.hasNext()) {
				User u = iter.next();
				if (u.getUserID() == userID) {
					pm.deletePersistent(u);
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
	public User editUser(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Extent<User> e = pm.getExtent(User.class, true);
			Iterator<User> iter = e.iterator();
			while (iter.hasNext()) {
				User u = iter.next();
				if (u.getUserID().equals(user.getUserID())) {
					u.setUserID(user.getUserID());
					u.setName(user.getName());
					u.setEmail(user.getEmail());
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
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		List<User> user = null;
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			Query q = pm.newQuery(User.class);
			q.declareParameters("String mail, String pass");
			q.setFilter("email == mail");
			q.setFilter("password == pass");
			user = (List<User>) q.execute(email, password);
			detached = (List<User>) pm.detachCopy(user);

		} catch (Exception e) {
			throw new NotFoundException();
		} finally {
			pm.close();
		}
		return detached.get(0);
	}
}
