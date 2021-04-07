package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

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
<<<<<<< HEAD
	public List<User> getUsers(String userID) {
=======
	public List<User> getUsers(Integer uid) {
>>>>>>> 4ed12e0ab9b4d2bc07071b35d8a0088cbab96d93
		// TODO Auto-generated method stub
		List<User> actions = null;
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);
<<<<<<< HEAD
			if (userID != null) {
				q.declareParameters("String user");
				q.setFilter("userID == user");
				actions = (List<User>) q.execute(userID);
=======
			if (uid != null) {
				q.declareParameters("Integer userID");
				q.setFilter("userID == userID");
				actions = (List<User>) q.execute(uid);
>>>>>>> 4ed12e0ab9b4d2bc07071b35d8a0088cbab96d93
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
<<<<<<< HEAD
	public List<Pilot> getPilots(String userID) {
		// TODO Auto-generated method stub
=======
	public List<Pilot> getPilots(String pid) {
>>>>>>> 4ed12e0ab9b4d2bc07071b35d8a0088cbab96d93
		List<Pilot> actions = null;
		List<Pilot> detached = new ArrayList<Pilot>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);
			if (userID != null) {
				q.declareParameters("String user");
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
	public void deleteUser(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.deletePersistent(user);

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
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		List<User> actions = null;
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			Query q = pm.newQuery(User.class);
			if (email != null && password != null) {
				q.declareParameters("String email, String password");
				q.setFilter("email == email");
				q.setFilter("password == password");
				actions = (List<User>) q.execute(email, password);
				detached = (List<User>) pm.detachCopyAll(actions);
			} else {
				actions = (List<User>) q.execute();
				detached = (List<User>) pm.detachCopyAll(actions);
			}

		} finally {
			pm.close();
		}
		return detached.size() > 0 ? detached.get(0) : null;
	}

}
