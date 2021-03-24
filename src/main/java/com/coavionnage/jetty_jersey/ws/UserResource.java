package com.coavionnage.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.User;

@Path("/users")
public class UserResource {

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void createUser(User u) {
		DAO.getUserDAO().addUser(u);
	}

	@POST
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifyUser(User user) {
		DAO.getUserDAO().editUser(user);
	}

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

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void removeUser(@PathParam("id") String uid) {
		User u = DAO.getUserDAO().getUsers(uid).get(0);
		DAO.getUserDAO().deleteUser(u);
	}
}
