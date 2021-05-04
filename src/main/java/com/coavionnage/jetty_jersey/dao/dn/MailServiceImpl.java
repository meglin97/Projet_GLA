package com.coavionnage.jetty_jersey.dao.dn;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.coavionnage.jetty_jersey.dao.Booking;
import com.coavionnage.jetty_jersey.dao.MailService;
import com.coavionnage.jetty_jersey.dao.User;

public class MailServiceImpl implements MailService {

	public static Object sendingMail(User u, Booking b, int bookNumber, String flight) {
		// TODO Auto-generated method stub
		String from = "uberplane_eidd@outlook.com";
		String to = u.getEmail();
		String host = "smtp-mail.outlook.com";
		String port = "587";
		String password = "choco0234xx";

		// Set system properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.setProperty("mail.smtp.user", from);
		properties.setProperty("mail.smtp.password", password);
		properties.setProperty("mail.smtp.starttls.enable", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set from email address
			message.setFrom(new InternetAddress(from, "UberPlane"));
			// Set the recipient email address
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
			// Set email subject
			message.setSubject("Booking confirmation");
			// Set email body
			message.setText("Hello " + u.getFirstName() + "," + "\nThank you for your bookings.\nYou booked "
					+ bookNumber + " places for the flight " + flight + " for " + b.getDateTime());
			// Set configs for sending email
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			// Send email
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("done");
			return "Email Sent! Check Inbox!";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
