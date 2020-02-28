package services.ticketsale.model;

import java.util.List;

import javax.ws.rs.core.Link;

public class Ticket {
  private Flight flight;
  private int seat;
  private List<Link> links;
  
  public Ticket(Flight flight) {
	  super();
	  this.flight = flight;
  }
  
  public Ticket(Ticket ticket) {
	  super();
	  this.flight = ticket.getFlight();
	  this.seat = ticket.getSeat();
	  this.links = ticket.getLinks();
  }

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

  public List<Link> getLinks() {
	  return links;
  }

  public void setLinks(List<Link> links) {
	  this.links = links;
  }
  
  @Override
  public String toString() {
	  return "Ticket [flight=" + flight.toString() + ", seat=" + seat + "]";
  }

  @Override
  public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((flight.getCode() == null) ? 0 : flight.getCode().hashCode());
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
	  if (flight.getCode() == null) {
		  if (other.flight.getCode() != null)
			  return false;
		  } else if (!flight.getCode().equals(other.flight.getCode()))
			  return false;
	  if (seat != other.seat)
		return false;
	  return true;
  }
  
}
