package com.coavionnage.jetty_jersey.dao;

import java.util.List;

public interface PilotDAO {

	List<Pilot> getPilots();

	Pilot addPilot(User u, int nbHours, int nbYears, String qualifications);

	void editPilot(User u, int nbHours, int nbYears, String qualifications);

}
