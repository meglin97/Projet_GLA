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

import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.FlightBooking;
import com.coavionnage.jetty_jersey.dao.FlightBookingDAOImpl;

@Path("/bookings")
public class FlightBookingResource {
	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public FlightBooking createFlightBooking(FlightBooking fb) {
		System.out.println("FlightBookingRessource.createFlightBooking()");
		// On cherche si le vol existe
		Optional<Flight> findFirst = FlightBookingDAOImpl.getFlight(fb.getFlightID());
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}

		// creation d'une vente
		FlightBooking newBookFlight = new FlightBooking();
		// newBookFlight.setBookingID(fb.getBookingID());
		newBookFlight.setFlightID(fb.getFlightID());
		newBookFlight.setPilot(fb.getPilot());
		newBookFlight.setTicketPrice(fb.getTicketPrice());
		newBookFlight.setNumberPlaces(fb.getNumberPlaces());

		FlightBookingDAOImpl.getAllBooking().add(newBookFlight);
		return newBookFlight;
	}

	@POST
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifyFlightBooking(FlightBooking fb) {
		System.out.println("FlightBookingRessource.modifyFlightBooking()");
		Optional<Flight> findFirst = FlightBookingDAOImpl.getFlight(fb.getFlightID());
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		FlightBooking newBookFlight = new FlightBooking();
		newBookFlight.setBookingID(fb.getBookingID());
		newBookFlight.setFlightID(fb.getFlightID());
		newBookFlight.setPilot(fb.getPilot());
		newBookFlight.setTicketPrice(fb.getTicketPrice());
		newBookFlight.setNumberPlaces(fb.getNumberPlaces());
		int index = FlightBookingDAOImpl.getAllBooking().indexOf(fb);
		FlightBookingDAOImpl.getAllBooking().set(index, newBookFlight);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FlightBooking> getFlightBookings() {
		System.out.println("FlightBookingRessource.getFlightBookings()");
		return FlightBookingDAOImpl.getAllBooking();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public FlightBooking getFlightBooking(@PathParam("id") String flihgtBookingID) {
		System.out.println("FlightBookingResource.getFlightBooking()");
		Optional<FlightBooking> findFirst = FlightBookingDAOImpl.getBooking(flihgtBookingID);
		if (!findFirst.isPresent()) {
			throw new NotFoundException("No matches found");
		}
		return findFirst.get();
	}

	@DELETE
	@Path("delete/{id}")
	public void removeFlightBooking(@PathParam("id") String flihgtBookingID) {
		System.out.println("FlightBookingResource.removeFlightBooking()");
		Optional<FlightBooking> findFirst = FlightBookingDAOImpl.getBooking(flihgtBookingID);
		if (findFirst.isPresent()) {
			FlightBookingDAOImpl.getAllBooking().remove(findFirst.get());
		}
	}
}
