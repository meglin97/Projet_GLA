package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface UserDAO {

	List<User> getUsers(String user);

	List<Pilot> getPilots(String pilot);

	void addUser(User u);

	void editUser(User u);

	void deleteUser(User u);

}
