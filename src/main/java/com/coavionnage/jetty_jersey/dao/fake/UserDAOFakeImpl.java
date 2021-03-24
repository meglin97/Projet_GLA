package com.coavionnage.jetty_jersey.dao.fake;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAO;

public class UserDAOFakeImpl implements UserDAO {
	private static List<User> users = new ArrayList<User>();
	private static ArrayList<Pilot> pilots = new ArrayList<Pilot>();

	static {
		users.add(new User("1", "Tony Stark", "ironman@mail.com", "pepperpotts"));
		users.add(new User("2", "Thor Odinson", "thor@mail.com", "jane"));
		users.add(new User("3", "Stark", "ironman@mail.com", "pepperpotts"));
		users.add(new User("4", "Odinson", "thor@mail.com", "jane"));
		pilots.add(new Pilot("5", "Shuvo Das", "sdas@mail.com", "password", 3, "formations, diplomes etc."));
		pilots.add(new Pilot("6", "huiting", "hlin@mail.com", "pwd", 4, "qualified"));
		users.addAll(pilots);
	}

	@Override
	public List<User> getUsers(String user) {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public List<Pilot> getPilots(String pilot) {
		// TODO Auto-generated method stub
		return pilots;
	}

	@Override
	public void addUser(User u) {
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

	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		try {
			getUsers(null).remove(u);
		} catch (NotFoundException e) {
			System.out.println("User not found");
		}
	}

	@Override
	public void editUser(User u) {
		// TODO Auto-generated method stub
		User user = getUsers(u.getUserID()).get(0);
		deleteUser(user);
		addUser(u);
	}

}
