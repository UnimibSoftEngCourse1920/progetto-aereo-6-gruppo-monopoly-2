package services.ticketsale.model.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

import services.ticketsale.model.Ticket;

public class DtoTicket {
	private String code;
	private String departureAirport;
	private String arrivalAirport;
	private String departureTime;
	private String arrivalTime;
	private double price;
	private int seat;
	private String holderName;
	private String holderSurname;
	@JsonIgnore
	private List<Link> links;
	
	public DtoTicket() {}
	
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
		
		if(ticket.getHolderName() == null)
			this.holderName = "";
		else
			this.holderName = ticket.getHolderName();
		
		if(ticket.getHolderSurname() == null)
			this.holderSurname =  "";
		else
			this.holderSurname = ticket.getHolderSurname();
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

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderSurname() {
		return holderSurname;
	}

	public void setHolderSurname(String holderSurname) {
		this.holderSurname = holderSurname;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
