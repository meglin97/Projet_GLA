package com.coavionnage.jetty_jersey.ws;

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

@Path("/pilots")
public class PilotResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response getPilot() {
		return Response.ok(DAO.getPilotDAO().getPilots()).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getPilot(@PathParam("id") Integer uid) {
		return Response.ok(DAO.getPilotDAO().getPilot(uid)).build();

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
		List<Pilot> list = DAO.getPilotDAO().getPilots();
		for (Pilot p : list) {
			if (p.getEmail().equals(u.getEmail())) {
				return Response.status(Status.BAD_REQUEST).entity("Error: pilot already exists").build();
			}
		}
		return Response.created(null).entity(DAO.getPilotDAO().addPilot(u, nbHours, expYears, qualifications)).build();

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
	@Path("/edit/{id}")
	public Response editingUser(@PathParam("id") Integer uid, @QueryParam("numberOfHours") int nbHours,
			@QueryParam("numberOfYears") int expYears, @QueryParam("qualifications") String qualifications) {
		if (uid == null) {
			throw new BadRequestException("User missing");
		}
		User u = DAO.getUserDAO().getUser(uid);
		try {
			return Response.created(null).entity(DAO.getPilotDAO().editPilot(u, nbHours, expYears, qualifications))
					.build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Cannot edit: user not found").build();
		}
	}
}
