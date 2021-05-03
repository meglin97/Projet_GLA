package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface UserDAO {

	List<User> getUsers();

	User getUser(Integer id);

	User addUser(User u);

	boolean deleteUser(Integer userID);

	User editUser(Integer id, String first, String last, String pass);

	User getUserByEmailAndPassword(String email, String password);

}
