package com.coavionnage.jetty_jersey.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.NotFoundException;

import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	private PersistenceManagerFactory pmf;

	public UserDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		List<User> users = null;
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);
			users = (List<User>) q.execute();
			detached = (List<User>) pm.detachCopyAll(users);

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
	public User getUser(Integer id) {
		for (User u : this.getUsers()) {
			if (u.getUserID().equals(id)) {
				return u;
			}
		}
		return null;
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return user;
	}

	@Override
	public boolean deleteUser(Integer userID) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		boolean bool = false;
		try {
			tx.begin();
			User u = pm.getObjectById(User.class, userID); // cherche si l'utilisateur existe
			if (u != null) {
				pm.deletePersistent(u);
				bool = true;
			}
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
	public User editUser(Integer id, String first, String last, String pass) {
		User u = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			u = pm.getObjectById(User.class, id);
			if (u != null) {
				if (first != null) {
					u.setFirstName(first);
				}
				if (last != null) {
					u.setLastName(last);
				}
				if (pass != null) {
					u.setPassword(pass);
				}
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		List<User> users = new ArrayList<User>();
		List<User> detached = new ArrayList<User>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);
			if (email != null && password != null) {
				q.declareParameters("String mail");
				q.setFilter("email == mail");

				users = (List<User>) q.execute(email);
				detached = (List<User>) pm.detachCopyAll(users);
			}
			for (User u : detached) {
				if (u.getPassword().equals(password)) {
					return u;
				}
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return null;
	}

}
