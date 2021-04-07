package test_datanucleus;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.Pilot;

public class DAOImplTest {

	@Test
	public void test() {
		Assert.assertEquals(0, DAO.getFlightDAO().getFlights(null).size());
		Assert.assertEquals(0, DAO.getFlightDAO().getBookings(null).size());
		Assert.assertEquals(0, DAO.getUserDAO().getPilots(null).size());

		Pilot pilot = new Pilot();
		pilot.setUserID(1);
		pilot.setName("Huiting");
		pilot.setExperience(5);
		pilot.setNumberOfHoursFlights(200);

		Booking book1 = new Booking();
		book1.setBookingID("book1");
		book1.setUser(pilot);

		Booking book2 = new Booking();
		book2.setBookingID("book1");
		book2.setUser(pilot);

		List<Booking> bookings = new ArrayList<Booking>();
		bookings.add(book1);
		bookings.add(book2);

		Flight flight = new Flight();
		flight.setFlightID("Air1");
		flight.setDepartureAirfield("Paris");
		flight.setArrivalAirfield("Lyon");
		flight.setBooking(bookings);
		flight.setPilot(pilot);

		DAO.getFlightDAO().addFlight(flight);
		Assert.assertEquals(1, DAO.getFlightDAO().getFlights(null).size());
		Assert.assertEquals(2, DAO.getFlightDAO().getBookings(null).size());
		Assert.assertEquals(1, DAO.getUserDAO().getPilots(null).size());
		DAO.getFlightDAO().getFlights("Paris");
		DAO.getFlightDAO().getBookings("book1");
	}
}
