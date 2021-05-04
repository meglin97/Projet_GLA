package com.coavionnage.jetty_jersey.dao;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	public static void sendMail(User u, Booking b, int bookNumber, String flight) {

		Properties props = new Properties();
		// props.put("mail.smtp.user", "uberplane_eidd@outlook.com");
		props.put("mail.smtp.host", "smtp.outlook.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("uberplane_eidd@outlook.com", "choco0234xx");
			}
		};
		try {
			Session session = Session.getInstance(props, auth);

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("uberplane_eidd@outlook.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(u.getEmail()));
			msg.setSubject("UberFly");
			msg.setText("Hello " + u.getFirstName() + "," + "\nThank you for your bookings.\nYou booked " + bookNumber
					+ " places for the flight " + flight);

			Transport.send(msg);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
