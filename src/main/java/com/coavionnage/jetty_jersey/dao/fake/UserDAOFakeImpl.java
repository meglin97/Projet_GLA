package com.coavionnage.jetty_jersey.dao.fake;

import java.util.ArrayList;
import java.util.List;

import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAO;

public class UserDAOFakeImpl implements UserDAO {
	private static List<User> users = new ArrayList<User>();
	private static ArrayList<Pilot> pilots = new ArrayList<Pilot>();

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public User addUser(User u) {
		// TODO Auto-generated method stub
		if (getUser(u.getUserID()) != null) {
			System.out.println("UserID already used");
			System.exit(0);
		}
		User user = new User();
		user.setUserID(u.getUserID());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		getUsers().add(user);
		return user;

	}

	@Override
	public boolean deleteUser(Integer user) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
