package test_datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import com.coavionnage.jetty_jersey.dao.FlightDAO;
import com.coavionnage.jetty_jersey.dao.dn.FlightDAOImpl;

public class DAOImplTest {

	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Coavionnage");
		FlightDAO flightDao = new FlightDAOImpl(pmf);

		Assert.assertEquals(0, flightDao.getFlights("1").size());
	}
}
