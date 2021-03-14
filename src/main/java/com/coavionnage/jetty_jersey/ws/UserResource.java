package com.coavionnage.jetty_jersey.ws;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coavionnage.jetty_jersey.dao.Passenger;
import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;
import com.coavionnage.jetty_jersey.dao.UserDAOImpl;

@Path("/users")
public class UserResource {

	public UserResource() {

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void createUser(User u) {
		System.out.println("UserDAOImpl.createUser()");
		Optional<User> findFirst = UserDAOImpl.getUser(u.getUserID());
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		String role = u.getRole();
		if (role == "Passenger") {
			Passenger newPassenger = new Passenger();
			newPassenger.setUserID(u.getUserID());
			newPassenger.setName(u.getName());
			newPassenger.setEmail(u.getEmail());
			newPassenger.setPassword(u.getPassword());
			newPassenger.setRole(u.getRole());
			newPassenger.setPlaceNum(((Passenger) u).getPlaceNum());
			newPassenger.setFlightID(((Passenger) u).getFlightID());
			UserDAOImpl.getAllUsers().add(newPassenger);
		} else if (role == "Pilot") {
			Pilot newPilot = new Pilot();
			newPilot.setUserID(u.getUserID());
			newPilot.setName(u.getName());
			newPilot.setEmail(u.getEmail());
			newPilot.setPassword(u.getPassword());
			newPilot.setRole(u.getRole());
			newPilot.setNumberOfHoursFlights(((Pilot) u).getNumberOfHoursFlights());
			newPilot.setExperience(((Pilot) u).getExperience());
			newPilot.setQualifications(((Pilot) u).getQualifications());
			UserDAOImpl.getAllUsers().add(newPilot);
		} else {
			User newUser = new User();
			newUser.setUserID(u.getUserID());
			newUser.setName(u.getName());
			newUser.setEmail(u.getEmail());
			newUser.setPassword(u.getPassword());
			UserDAOImpl.getAllUsers().add(newUser);
		}
	}

	@POST
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifyUser(User u) {
		System.out.println("UserDAOImpl.modifyUser()");
		Optional<User> findFirst = UserDAOImpl.getUser(u.getUserID());
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		int index = UserDAOImpl.getAllUsers().indexOf(u);
		String role = u.getRole();
		if (role == "Passenger") {
			Passenger newPassenger = new Passenger();
			newPassenger.setUserID(u.getUserID());
			newPassenger.setName(u.getName());
			newPassenger.setEmail(u.getEmail());
			newPassenger.setPassword(u.getPassword());
			newPassenger.setRole(u.getRole());
			newPassenger.setPlaceNum(((Passenger) u).getPlaceNum());
			newPassenger.setFlightID(((Passenger) u).getFlightID());
			UserDAOImpl.getAllUsers().set(index, newPassenger);
		} else if (role == "Pilot") {
			Pilot newPilot = new Pilot();
			newPilot.setUserID(u.getUserID());
			newPilot.setName(u.getName());
			newPilot.setEmail(u.getEmail());
			newPilot.setPassword(u.getPassword());
			newPilot.setRole(u.getRole());
			newPilot.setNumberOfHoursFlights(((Pilot) u).getNumberOfHoursFlights());
			newPilot.setExperience(((Pilot) u).getExperience());
			newPilot.setQualifications(((Pilot) u).getQualifications());
			UserDAOImpl.getAllUsers().set(index, newPilot);
		} else {
			User newUser = new User();
			newUser.setUserID(u.getUserID());
			newUser.setName(u.getName());
			newUser.setEmail(u.getEmail());
			newUser.setPassword(u.getPassword());
			UserDAOImpl.getAllUsers().set(index, newUser);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		System.out.println("UserDAOImpl.getUsers()");
		return UserDAOImpl.getAllUsers();
	}

	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") String uid) {
		System.out.println("UserDAOImpl.getUser()");
		Optional<User> findFirst = UserDAOImpl.getUser(uid);
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		return findFirst.get();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void removeUser(@PathParam("id") String uid) {
		System.out.println("UserDAOImpl.removeUser()");
		Optional<User> findFirst = UserDAOImpl.getUser(uid);
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		UserDAOImpl.getAllUsers().remove(findFirst.get());
	}
}
