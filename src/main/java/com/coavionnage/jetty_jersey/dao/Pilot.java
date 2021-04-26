package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public class Pilot extends User {

	@Persistent
	private int numberOfHoursFlights;
	@Persistent
	private int yearsOfExperience;
	@Persistent
	private String qualifications;

	public Pilot() {

	}

	public Pilot(Integer id, String name, String email, String password, int years, String qualifications) {
		super(id, name, email, password);

		this.yearsOfExperience = years;
		this.qualifications = qualifications;
		this.numberOfHoursFlights = 0;
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
