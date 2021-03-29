package com.coavionage.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coavionage.jetty_jersey.dao.DAO;
import com.coavionage.jetty_jersey.dao.User;

@Path("/users")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return DAO.getUserDAO().getUsers(null);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public User getUser(@PathParam("id") String uid) {
		return DAO.getUserDAO().getUsers(uid).get(0);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public User addUser(User user) {
		if (user == null) {
			throw new BadRequestException("User missing");
		}
		return DAO.getUserDAO().addUser(user);
	}
}
