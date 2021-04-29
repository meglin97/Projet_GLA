package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public class Pilot extends User {

	private int numberOfHoursFlights;
	private int yearsOfExperience;
	private String qualifications;

	public Pilot(Integer id, String firstname, String lastname, String email) {
		super(id, firstname, lastname, email);
	}

	public Pilot(Integer id, String firstname, String lastname, String email, int nbHours, int nbYears,
			String qualification) {
		super(id, firstname, lastname, email);
		this.numberOfHoursFlights = nbHours;
		this.yearsOfExperience = nbYears;
		this.qualifications = qualification;
	}

	public Pilot() {
		// TODO Auto-generated constructor stub

	}

	public int getNumberOfHoursFlights() {
		return numberOfHoursFlights;
	}

	public void setNumberOfHoursFlights(int numberOfHoursFlights) {
		this.numberOfHoursFlights = numberOfHoursFlights;
	}

	public int getExperience() {
		return yearsOfExperience;
	}

	public void setExperience(int years) {
		this.yearsOfExperience = years;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

}
