package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface UserDAO {

	List<User> getUsers(Integer userID);

	List<Pilot> getPilots(Integer userID);

	User addUser(User u);

	Pilot addPilot(Integer pid, int nbHours, int expYears, String qualifications);

	boolean deleteUser(Integer userID);

	User editUser(User user);

	User getUserByEmailAndPassword(String email, String password);
}
