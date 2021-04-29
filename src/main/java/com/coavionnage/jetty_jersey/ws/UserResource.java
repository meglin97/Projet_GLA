package com.coavionnage.jetty_jersey.ws;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;

@Path("/users")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		List<User> list = DAO.getUserDAO().getUsers();
		Collections.sort(list, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getUserID().compareTo(o2.getUserID());
			}
		});
		return Response.ok(list).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getUser(@PathParam("id") Integer uid) {
		User user = DAO.getUserDAO().getUser(uid);

		try {
			return Response.ok(user).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pilots")
	public Response getPilot() {
		return Response.ok(DAO.getUserDAO().getPilots(null)).build();

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
	@Path("/add/{id}")
	public Response addPilot(@PathParam("id") Integer uid, @QueryParam("numberOfHours") int nbHours,
			@QueryParam("numberOfYears") int expYears, @QueryParam("qualifications") String qualifications) {
		if (uid == null) {
			throw new BadRequestException("User missing");
		}
		User u = DAO.getUserDAO().getUser(uid);
		Pilot pilot = new Pilot(uid, u.getFirstName(), u.getLastName(), u.getEmail());
		User newUser = pilot;
		try {
			return Response.created(null).entity(DAO.getUserDAO().addPilot(newUser)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: cannot add pilot").build();
		}

	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
		User u = DAO.getUserDAO().getUserByEmailAndPassword(email, password);
		if (u != null) {
			return Response.ok().header("email", email).header("password", password).entity(u).build();
		}
		return Response.status(Status.NOT_FOUND).entity("userID or password wrong").build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public Response deleteUser(@PathParam("id") Integer uid) {
		if (uid == null) {
			throw new BadRequestException("User id missing");
		}
		try {
			return Response.created(null).entity(DAO.getUserDAO().deleteUser(uid)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
		}
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
			return Response.status(Status.BAD_REQUEST).entity("Cannot edit: user not found").build();
		}
	}
}
