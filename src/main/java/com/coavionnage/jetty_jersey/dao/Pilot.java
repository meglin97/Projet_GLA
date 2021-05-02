package com.coavionnage.jetty_jersey.dao;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public class Pilot {
	@PrimaryKey
	private Integer userID;

	private String firstname;
	private String lastname;

	private String email;

	private int numberOfHoursFlights;
	private int yearsOfExperience;
	private String qualifications;

	public Pilot(Integer id, String firstname, String lastname, String email, int nbHours, int nbYears,
			String qualification) {
		this.userID = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.numberOfHoursFlights = nbHours;
		this.yearsOfExperience = nbYears;
		this.qualifications = qualification;
	}

	public Pilot() {
		// TODO Auto-generated constructor stub

	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
