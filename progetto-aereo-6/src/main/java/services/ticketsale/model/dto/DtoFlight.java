package services.ticketsale.model.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

import services.ticketsale.model.Flight;

public class DtoFlight {

	private String code;
	private String departureAirport;
	private String arrivalAirport;
	private String departureTime;
	private String arrivalTime;
	private double price;
	private int seatsNumber;
	@JsonIgnore
	private List<Link> links;

	public DtoFlight() {}

	public void buildDtoFlight(Flight flight) {
		this.code = flight.getCode();
		this.departureAirport = flight.getDepartureAirport();
		this.arrivalAirport = flight.getArrivalAirport();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		this.departureTime = sdf.format(flight.getDepartureTime());
		this.arrivalTime = sdf.format(flight.getArrivalTime());
		this.price = flight.getPrice();
		this.seatsNumber = flight.getSeatsNumber();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
