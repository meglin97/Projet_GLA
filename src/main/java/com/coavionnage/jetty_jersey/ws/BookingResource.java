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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	public Response addFlightBooking(Booking fb) {
		if (fb == null) {
			throw new BadRequestException("User missing");
		}
		try {
			return Response.created(null).entity(DAO.getBookingDAO().addBooking(fb)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: Booking already exists").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Booking getBooking(@PathParam("id") String bookingID) {

		return DAO.getBookingDAO().getBookings(bookingID).get(0);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/edit")
	public Response editingBooking(Booking booking) {
		if (booking == null) {
			throw new BadRequestException("booking missing");
		}
		try {
			return Response.created(null).entity(DAO.getBookingDAO().editBooking(booking)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Booking not found").build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteBooking(@PathParam("id") Integer bookingID) {
		if (bookingID == null) {
			throw new BadRequestException("booking missing");
		}
		try {
			return Response.created(null).entity(DAO.getBookingDAO().deleteBooking(bookingID)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Booking not found").build();
		}
	}
}
