package com.coavionnage.jetty_jersey.ws;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightBookingDAOImpl;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {
	public FlightResource() {

	}

	@GET
	public List<Flight> getFlights() {
		System.out.println("getFlights");
		return FlightBookingDAOImpl.getAllFlight();
	}

	@GET
	@Path("{id}")
	public Flight getFlight(@PathParam("id") String flightID) {
		System.out.println("getFlight");
		Optional<Flight> flightByID = FlightBookingDAOImpl.getFlight(flightID);
		if (!flightByID.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		return flightByID.get();
	}

	@GET
	@Path("/search")
	public Response searchFlightByCriteria(@QueryParam("departure") String departure,
			@QueryParam("arrival") String arrival, @QueryParam("departure_time") String departureTime) {
		System.out.println("FlightResource.searchByCriteria()");
		String[] departureDateArray = departureTime.split("-");
		LocalDateTime departureDate = LocalDateTime.of(Integer.parseInt(departureDateArray[0]), Integer.parseInt(departureDateArray[1]), Integer.parseInt(departureDateArray[2]), 0, 0, 0);
		
		return Response.ok().header("departure", departure).header("arrival", arrival)
				.header("departure_time", departureTime).entity(FlightBookingDAOImpl.searchFlights(departure, arrival, departureDate))
				.build();
	}

	@Path("/bookings")
	public FlightBookingResource getFlightBookingResource() {
		return new FlightBookingResource();
	}

	@Path("/users")
	public UserResource getUserResource() {
		return new UserResource();
	}
}
