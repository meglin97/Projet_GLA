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
import com.coavionnage.jetty_jersey.dao.Flight;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return DAO.getFlightDAO().getFlights(null);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{departure}")
	public Response getFlight(@PathParam("departure") String departure) {
		Flight flight = DAO.getFlightDAO().getFlights(departure).get(0);

		if (flight != null)
			return Response.ok(flight).build();

		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addFlight(Flight flight) {
		if (flight == null) {
			throw new BadRequestException("Flight missing");
		}
		try {
			return Response.created(null).entity(DAO.getFlightDAO().addFlight(flight)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: one or more flights already exist").build();
		}
	}

	@GET
	@Path("/search")
	public Response searchFlightByCriteria(@QueryParam("departure") String departure,
			@QueryParam("arrival") String arrival, @QueryParam("departure_time") String departureTime) {
		System.out.println("FlightResource.searchByCriteria()");

		return Response.ok().header("departure", departure).header("arrival", arrival)
				.header("departure_time", departureTime).entity(DAO.getFlightDAO().getFlights(departure)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/edit")
	public Response editingFlight(Flight flight) {
		if (flight == null) {
			throw new BadRequestException("Flight missing");
		}
		try {
			return Response.created(null).entity(DAO.getFlightDAO().editFlight(flight)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Flight not found").build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteFlight(@PathParam("id") String flightID) {
		if (flightID == null) {
			throw new BadRequestException("Flight id missing");
		}
		try {
			return Response.created(null).entity(DAO.getFlightDAO().deleteFlight(flightID)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Cannot edit: flight not found").build();
		}
	}
}
