package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface PilotDAO {

	List<Pilot> getPilots(Integer userID);

	Pilot addPilot(User u, int nbHours, int nbYears, String qualifications);

	Pilot editPilot(User u, int nbHours, int nbYears, String qualifications);

}
