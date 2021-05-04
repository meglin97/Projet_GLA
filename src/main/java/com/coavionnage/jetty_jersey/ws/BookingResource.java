package com.coavionnage.jetty_jersey.ws;

import java.util.ArrayList;
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
import com.coavionnage.jetty_jersey.dao.User;

@Path("/bookings")
public class BookingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookings() {
		return Response.ok().entity(DAO.getBookingDAO().getBookings()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getBooking(@PathParam("id") Integer bookingID) {
		if (bookingID == null) {
			throw new BadRequestException("User id is missing");
		}
		try {
			return Response.created(null).entity(DAO.getBookingDAO().getBooking(bookingID)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: Booking already exists").build();
		}
	}

	@GET
	@Path("/totalBookings")
	public Response totalBookings() {
		try {
			return Response.created(null).entity(DAO.getBookingDAO().totalBooking()).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Error: Booking already exists").build();
		}
	}

	@GET
	@Path("/userBookings/{id}")
	public Response userBookings(@PathParam("id") Integer userID) {
		if (userID == null) {
			throw new BadRequestException("User id is missing");
		}
		try {
			return Response.created(null).entity(DAO.getBookingDAO().userBookings(userID)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/flightBookings/{id}")
	public Response flightBookings(@PathParam("id") Integer flightID) {
		if (flightID == null) {
			throw new BadRequestException("flight id is missing");
		}
		try {
			return Response.created(null).entity(DAO.getBookingDAO().bookingNumber(flightID)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	/*
	 * @PUT
	 * 
	 * @Path("/add")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response addBooking(Booking
	 * book) { if (book == null) { throw new BadRequestException("User missing"); }
	 * try { return
	 * Response.created(null).entity(DAO.getBookingDAO().addBooking(book)).build();
	 * } catch (Exception e) { return
	 * Response.status(Status.BAD_REQUEST).entity("Error: Booking already exists").
	 * build(); } }
	 */

	@PUT
	@Path("/add/{nb}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBookings(@PathParam("nb") int nb, Booking booking) {
		List<Booking> list = new ArrayList<Booking>();
		if (booking == null) {
			throw new BadRequestException("User missing");
		}
		int places = DAO.getFlightDAO().flightPlaces(booking.getFlightID());
		int bookings = DAO.getBookingDAO().bookingNumber(booking.getFlightID());
		if (places - bookings < nb) {
			return Response.status(Status.NOT_ACCEPTABLE).entity("Cannot add bookings : not enough available places ")
					.build();
		}
		int i = 0;
		while (i < nb) {
			list.add(DAO.getBookingDAO().addBooking(booking));
			i++;
		}
		User u = DAO.getUserDAO().getUser(booking.getUser());
		String flight = DAO.getFlightDAO().getFlight(booking.getFlightID()).toString();
		try {
			DAO.getBookingDAO().sendingMail(u, booking, list.size(), flight);
			return Response.ok().entity("Email sended").build();
		} catch (Exception e) {
			return Response.status(javax.transaction.Status.STATUS_COMMITTED).build();
		}

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
