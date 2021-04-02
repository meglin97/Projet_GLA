package com.coavionnage.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.Flight;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Flight addFlight(Flight flight) {
		return DAO.getFlightDAO().addFlight(flight);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return DAO.getFlightDAO().getFlights(null);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{departure}")
	public Flight getFlight(@PathParam("departure") String departure) {
		return DAO.getFlightDAO().getFlights(departure).get(0);
	}

	@GET
	@Path("/search")
	public Response searchFlightByCriteria(@QueryParam("departure") String departure,
			@QueryParam("arrival") String arrival, @QueryParam("departure_time") String departureTime) {
		System.out.println("FlightResource.searchByCriteria()");

		return Response.ok().header("departure", departure).header("arrival", arrival)
				.header("departure_time", departureTime).entity(DAO.getFlightDAO().getFlights(departure)).build();
	}
}
