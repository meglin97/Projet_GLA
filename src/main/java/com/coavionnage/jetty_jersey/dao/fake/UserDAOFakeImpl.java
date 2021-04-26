package com.coavionnage.jetty_jersey.dao.fake;

import java.util.ArrayList;
import java.util.List;

import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAO;

public class UserDAOFakeImpl implements UserDAO {
	private static List<User> users = new ArrayList<User>();
	private static ArrayList<Pilot> pilots = new ArrayList<Pilot>();

	static {
		users.add(new User(1, "Tony Stark", "ironman@mail.com", "pepperpotts"));
		users.add(new User(2, "Thor Odinson", "thor@mail.com", "jane"));
		users.add(new User(3, "Stark", "ironman@mail.com", "pepperpotts"));
		users.add(new User(4, "Odinson", "thor@mail.com", "jane"));
		pilots.add(new Pilot(5, "Shuvo Das", "sdas@mail.com", "password", 3, "formations, diplomes etc."));
		pilots.add(new Pilot(6, "huiting", "hlin@mail.com", "pwd", 4, "qualified"));
		users.addAll(pilots);
	}

	@Override
	public List<User> getUsers(Integer user) {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public List<Pilot> getPilots(Integer pilot) {
		// TODO Auto-generated method stub
		return pilots;
	}

	@Override
	public User addUser(User u) {
		// TODO Auto-generated method stub
		if (getUsers(u.getUserID()) != null) {
			System.out.println("UserID already used");
			System.exit(0);
		}
		User user = new User();
		user.setUserID(u.getUserID());
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		getUsers(null).add(user);
		return user;

	}

	@Override
	public boolean deleteUser(Integer user) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public User editUser(User user) {
		return user;
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pilot addPilot(Integer u) {
		// TODO Auto-generated method stub
		return null;
	}

}
