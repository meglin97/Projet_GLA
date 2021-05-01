package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface UserDAO {

	List<User> getUsers();

	User getUser(Integer id);

	// List<Pilot> getPilots();

	User addUser(User u);

	// Pilot addPilot(User u, int nbHours, int nbYears, String qualifications);

	boolean deleteUser(Integer userID);

	User editUser(User user);

	User getUserByEmailAndPassword(String email, String password);

	// void editPilot(User u, int nbHours, int nbYears, String qualifications);

}
