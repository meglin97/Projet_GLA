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
import com.coavionnage.jetty_jersey.dao.Pilot;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {

	// shows only available flights
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvailableFlights() {
		List<Flight> all = DAO.getFlightDAO().getFlights();
		if (all == null) {
			return Response.status(Status.NOT_FOUND).entity("No flights").build();
		}
		for (Flight f : all) {
			int places = DAO.getFlightDAO().getFlight(f.getFlightID()).getNumberPlaces();
			int bookings = DAO.getBookingDAO().bookingNumber(f.getFlightID());
			if (bookings == places) {
				DAO.getFlightDAO().deleteFlight(f.getFlightID());
			}
		}
		return Response.ok().entity(DAO.getFlightDAO().getFlights()).build();
	}

	// shows all flights
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllFlights() {
		return Response.created(null).entity(DAO.getFlightDAO().getFlights()).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getFlight(@PathParam("id") Integer fid) {
		if (fid == null) {
			throw new BadRequestException("flight ID missed");
		}
		try {
			return Response.created(null).entity(DAO.getFlightDAO().getFlight(fid)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: Flight not found").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addFlight(Flight flight) {
		if (flight == null) {
			throw new BadRequestException("Flight information missed");
		}
		Pilot pilot = DAO.getPilotDAO().getPilot(flight.getpilot());
		if (pilot == null) {
			return Response.status(Status.BAD_REQUEST).entity("Pilot not exists").build();
		}
		try {
			return Response.ok().entity(DAO.getFlightDAO().addFlight(flight)).build();
		} catch (Exception e) {

			return Response.status(Status.BAD_REQUEST).entity("Error: flight already exists").build();
		}
	}

	@GET
	@Path("/search")
	public Response searchByCriteria(@QueryParam("departure") String departure, @QueryParam("arrival") String arrival,
			@QueryParam("departure_date") String departure_date, @QueryParam("arrival_date") String arrival_date) {

		return Response.ok()
				.entity(DAO.getFlightDAO().searchByCriteria(departure, arrival, departure_date, arrival_date)).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/edit")
	public Response editFlight(Flight flight) {
		if (flight == null) {
			throw new BadRequestException("Flight missing");
		}
		boolean bool = DAO.getFlightDAO().editFlight(flight);
		if (bool == true) {

			return Response.ok().entity("Flight edited").build();
		}
		return Response.status(Status.BAD_REQUEST).entity("Cannot edit: flight not found").build();

	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteFlight(@PathParam("id") Integer flightID) {
		if (flightID == null) {
			throw new BadRequestException("Flight id missing");
		}
		boolean bool = DAO.getFlightDAO().deleteFlight(flightID);
		if (bool == true) {
			return Response.ok().entity("Flight deleted").build();
		}

		return Response.status(Status.NOT_FOUND).entity("Cannot delete: flight not found").build();

	}

	@GET
	@Path("/totalflights")
	public Response totalFLight() {
		try {
			return Response.created(null).entity("There are " + DAO.getFlightDAO().flightNumber() + " flights in total")
					.build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("No flights").build();
		}
	}
}
