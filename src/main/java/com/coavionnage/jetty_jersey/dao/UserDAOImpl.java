package com.coavionnage.jetty_jersey.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

	private static List<User> users = new ArrayList<User>();
	private static ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private static ArrayList<Pilot> pilots = new ArrayList<Pilot>();

	static {
		passengers.add(new Passenger("1", "Tony Stark", "ironman@mail.com", "pepperpotts", "Passenger", "1", "30A"));
		passengers.add(new Passenger("2", "Thor Odinson", "thor@mail.com", "jane", "Passenger", "1", "30B"));
		passengers.add(new Passenger("3", "Stark", "ironman@mail.com", "pepperpotts", "Passenger", "2", "30c"));
		passengers.add(new Passenger("4", "Odinson", "thor@mail.com", "jane", "Passenger", "2", "30d"));
		pilots.add(new Pilot("5", "Shuvo Das", "sdas@mail.com", "password", "Pilot", 3, "formations, diplomes etc."));
		pilots.add(new Pilot("6", "huiting", "hlin@mail.com", "pwd", "Pilot", 4, "qualified"));
		users.addAll(passengers);
		users.addAll(pilots);
	}

	public static List<Passenger> getAllPassengers() {
		return passengers;
	}

	public static List<Pilot> getAllPilots() {
		return pilots;
	}

	public static List<User> getAllUsers() {
		users.addAll(passengers);
		users.addAll(pilots);
		return users;
	}

	public static Optional<User> getUser(String uid) {
		return UserDAOImpl.getAllUsers().stream().filter(user -> user.getUserID().equals(uid)).findFirst();
	}

}
