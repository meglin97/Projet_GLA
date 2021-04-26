package test_datanucleus;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.Flight;
import com.coavionnage.jetty_jersey.dao.Pilot;
import com.coavionnage.jetty_jersey.dao.User;

public class DAOImplTest {

	@Test
	public void test() {
		List<User> users = new ArrayList<User>();
		ArrayList<Pilot> pilots = new ArrayList<Pilot>();
		ArrayList<Flight> flights = new ArrayList<Flight>();
		ArrayList<Booking> books = new ArrayList<Booking>();

		Pilot pilot = new Pilot(1, "Huiting", "hlin@mail.fr", "password", 5, "Co-pilot");
		users.add(pilot);
		pilots.add(pilot);

		Booking book = new Booking(1, 1, 1, "pending");
		books.add(book);

		// LocalDateTime now = LocalDateTime.now();
		// DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE dd-MM-yyyy
		// HH:mm:ss");
		// if (book.getDateTime().isEqual(now)) {Assert.assertTrue(true);}
		// System.err.print(book.toString());
		// System.err.print("\n" + now.format(format));
		Assert.assertTrue(users.size() == 1);
		Assert.assertTrue(pilots.size() == 1);
		Assert.assertTrue(flights.size() == 0);
		Assert.assertTrue(books.size() == 1);
	}
}
