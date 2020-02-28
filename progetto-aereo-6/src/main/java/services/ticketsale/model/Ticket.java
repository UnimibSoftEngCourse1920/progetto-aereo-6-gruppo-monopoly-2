package services.ticketsale.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Link;

public class Ticket {
	private String code;
	private String departureAirport;
	private String arrivalAirport;
	private Date departureTime;
	private Date arrivalTime;
	private double price;
	private int seat;
	private List<Link> links;
	
	
	public Ticket(String jsonString) {
		super();
		String[] attr = jsonToAttr(jsonString);
		this.code = attr[0];
		this.departureAirport = attr[1];
		this.arrivalAirport = attr[2];
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		try {
			this.departureTime = sdf.parse(attr[3]);
			this.arrivalTime = sdf.parse(attr[4]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.price = Double.parseDouble(attr[5]);
		this.seat = Integer.parseInt(attr[6]);
		this.links = null;
	}

	public Ticket(Ticket ticket) {
		super();
		this.code = ticket.getCode();
		this.departureAirport = ticket.getDepartureAirport();
		this.arrivalAirport = ticket.getArrivalAirport();
		this.departureTime = ticket.getDepartureTime();
		this.arrivalTime = ticket.getArrivalTime();
		this.price = ticket.getPrice();
		this.seat = ticket.getSeat();
		this.links = ticket.getLinks();
	}

	public Ticket(Flight flight) {
		super();
		this.code = flight.getCode();
		this.departureAirport = flight.getDepartureAirport();
		this.arrivalAirport = flight.getArrivalAirport();
		this.departureTime = flight.getDepartureTime();
		this.arrivalTime = flight.getArrivalTime();
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

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
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

	@Override
	public String toString() {
		return "Ticket [code=" + code + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", price=" + price + ", seat=" + seat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + seat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (seat != other.seat)
			return false;
		return true;
	}
	
	public static String[] jsonToAttr(String jsonString) {
		String[] ret = new String[7];
		int i = 0;
		int j = 0;
		String sub = null;
		
		i = jsonString.indexOf("code")+7;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[0] = jsonString.substring(i, j);
		
		i = jsonString.indexOf("departureAirport")+19;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[1] = jsonString.substring(i, j);
		
		i = jsonString.indexOf("arrivalAirport")+17;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[2] = jsonString.substring(i, j);
		
		int x = 0;
		
		i = jsonString.indexOf("departureTime")+16;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[3] = jsonString.substring(i, j);
		x = ret[3].lastIndexOf(":");
		ret[3] = ret[3].substring(0, x).replaceAll("T", " ");
		
		i = jsonString.indexOf("arrivalTime")+14;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[4] = jsonString.substring(i, j);
		x = ret[4].lastIndexOf(":");
		ret[4] = ret[4].substring(0, x).replaceAll("T", " ");
		
		i = jsonString.indexOf("price")+7;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")-1+i;
		ret[5] = jsonString.substring(i, j);
		
		i = jsonString.indexOf("seat")+6;
		sub = jsonString.substring(i);
		j = sub.indexOf("}")+i;
		ret[6] = jsonString.substring(i, j);
		
		return ret;
	}
}
