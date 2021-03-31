package com.coavionnage.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.DAO;

@Path("/bookings")
public class BookingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Booking> getBookings() {
		return DAO.getBookingDAO().getBookings(null);
	}

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createFlightBooking(Booking fb) {
		DAO.getBookingDAO().addBooking(fb);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Booking getBooking(@PathParam("id") String bookingID) {

		return DAO.getBookingDAO().getBookings(bookingID).get(0);
	}
}
