package services.ticketsale.model;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DtoTicket {
	private String code;
	private String departureAirport;
	private String arrivalAirport;
	private String departureTime;
	private String arrivalTime;
	private double price;
	private int seat;
	@JsonIgnore
	private List<Link> links;
	
	public DtoTicket() {}
	
	public DtoTicket(DtoTicket ticket) {
		super();
		this.code = ticket.getCode();
		this.departureAirport = ticket.getDepartureAirport();
		this.arrivalAirport = ticket.getArrivalAirport();
		this.departureTime = ticket.getDepartureTime();
		this.arrivalTime = ticket.getArrivalTime();
		this.price = ticket.getPrice();
		this.seat = ticket.getSeat();
	}
	
	public DtoTicket(Ticket ticket) {
		super();
		this.code = ticket.getFlight().getCode();
		this.departureAirport = ticket.getFlight().getDepartureAirport();
		this.arrivalAirport = ticket.getFlight().getArrivalAirport();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		this.departureTime = sdf.format(ticket.getFlight().getDepartureTime());
		this.arrivalTime = sdf.format(ticket.getFlight().getArrivalTime());
		this.price = ticket.getFlight().getPrice();
		this.seat = ticket.getSeat();
	}
	
	public DtoTicket(Flight flight) {
		this.code = flight.getCode();
		this.departureAirport = flight.getDepartureAirport();
		this.arrivalAirport = flight.getArrivalAirport();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		this.departureTime = sdf.format(flight.getDepartureTime());
		this.arrivalTime = sdf.format(flight.getArrivalTime());
		this.price = flight.getPrice();
		this.seat = 0;
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

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
