package services.ticketsale.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Flight {
	private String code;
	private String departureAirport;
	private String arrivalAirport;
	private Date departureTime;
	private Date arrivalTime;
	private double price;
	private int seatsNumber;
	private boolean[] seats;

	public Flight(String code, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime,
			double price, int seatsNumber) {
		super();
		this.code = code;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			this.departureTime = sdf.parse(departureTime);
			this.arrivalTime = sdf.parse(arrivalTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.price = price;
		this.seatsNumber = seatsNumber;
		this.seats = new boolean[seatsNumber];
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

	public int getSeatsNumber() {
		return seatsNumber;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public boolean getSeats(int i) {
		return seats[i];
	}

	public void setSeats(int i, boolean occupy) {
		this.seats[i] = occupy;
	}

	public boolean[] getSeats() {
		return seats;
	}

	public void setSeats(boolean[] seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Flight [code=" + code + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", seatsNumber=" + seatsNumber
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Flight))
			return false;
		Flight other = (Flight) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}


}
