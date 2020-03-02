package services.ticketsale.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import services.ticketsale.model.Flight;

public class FlightRepository {
	private Map<String, Flight> flights;
	private static FlightRepository instance = null;
	
	public static FlightRepository getInstance() {
		if (instance == null) {
			instance = new FlightRepository();
	    }
		return instance;
	}
	
	private FlightRepository() {
	    this.flights = new HashMap<>();
	    this.flights.put("C10000", new Flight("C10000", "Milano", "Roma", 
	    		"10-10-2020 10:10:00", "10-10-2020 11:10:00", 10, 100));
	    this.flights.put("C10001", new Flight("C10001", "Roma", "Milano",
	    		"10-10-2020 16:10:00", "10-10-2020 17:10:00", 10, 100));
	}
	
	public Collection<Flight> findAll() {
		return this.flights.values();
	}
	
	public Flight find(String code) {
	    return this.flights.get(code);
	}

}
