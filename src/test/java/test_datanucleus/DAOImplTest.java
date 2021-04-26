package test_datanucleus;

import org.junit.Assert;
import org.junit.Test;

import com.coavionnage.jetty_jersey.dao.DAO;
import com.coavionnage.jetty_jersey.dao.Flight;

public class DAOImplTest {

	@Test
	public void test() {
		Assert.assertEquals(0, DAO.getFlightDAO().getFlights().size());
		Assert.assertEquals(0, DAO.getUserDAO().getPilots(null).size());

		Flight flight = new Flight();
		flight.setDepartureAirfield("Paris");
		flight.setArrivalAirfield("Lyon");

		DAO.getFlightDAO().addFlight(flight);
		Assert.assertEquals(1, DAO.getFlightDAO().getFlights().size());
		Assert.assertEquals(1, DAO.getUserDAO().getPilots(null).size());

	}
}
