package com.coavionnage.jetty_jersey.ws;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.User;

@Path("/users")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> list = DAO.getUserDAO().getUsers(null);
		Collections.sort(list, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getUserID().compareTo(o2.getUserID());
			}

		});
		return list;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getUser(@PathParam("id") Integer uid) {
		User users = DAO.getUserDAO().getUsers(uid).get(0);

		if (users != null)
			return Response.ok(users).build();

		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addUser(User user) {
		if (user == null) {
			throw new BadRequestException("User missing");
		}

		try {
			return Response.created(null).entity(DAO.getUserDAO().addUser(user)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: email already used").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(Map<String, String> request) {
		User user = DAO.getUserDAO().getUserByEmailAndPassword(request.get("email"), request.get("password"));

		if (user == null)
			return Response.status(Status.UNAUTHORIZED).entity("Authentication error: email or password incorrect")
					.build();

		return Response.ok(user).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public Response deleteUser(@PathParam("id") Integer uid) {
		User users = DAO.getUserDAO().getUsers(uid).get(0);
		if (users == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		DAO.getUserDAO().deleteUser(users);
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/edit")
	public Response editingUser(User user) {
		if (user == null) {
			throw new BadRequestException("User missing");
		}
		try {
			return Response.created(null).entity(DAO.getUserDAO().editUser(user)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
		}
	}
}
