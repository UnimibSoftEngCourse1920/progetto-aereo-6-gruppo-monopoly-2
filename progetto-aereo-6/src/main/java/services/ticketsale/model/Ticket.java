package services.ticketsale.model;

public class Ticket {
	private Flight flight;
	private int seat;
	private String holderName;
	private String holderSurname;
	
	public Ticket() {}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
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
	
	public void cloneTicket(Ticket ticket) {
		this.flight = ticket.getFlight();
		this.seat = ticket.getSeat();
		this.holderName = ticket.getHolderName();
		this.holderSurname = ticket.getHolderSurname();
	}
	
	public void incompleteTicket(Flight flight) {
		this.flight = flight;
		this.seat = 0;
		this.holderName = null;
		this.holderSurname = null;
	}
	
	public void completeTicket(int seat, String holderName, String holderSurname) {
		this.seat = seat;
		this.holderName = holderName;
		this.holderSurname = holderSurname;
	}
	
	@Override
	public String toString() {
		return "Ticket [flight=" + flight + ", seat=" + seat 
				+ ", holderName=" + holderName + ", holderSurname=" + holderSurname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + seat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ticket))
			return false;
		Ticket other = (Ticket) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (seat != other.seat)
			return false;
		return true;
	}

}
