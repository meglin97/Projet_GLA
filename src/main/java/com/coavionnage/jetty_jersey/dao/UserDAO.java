package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface UserDAO {

	List<User> getUsers(String user);

	List<Pilot> getPilots(String pilot);

	User addUser(User u);

	void deleteUser(User user);

	void editUser(User user);

}
