package services.ticketsale.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import services.auth.model.User;
import services.customer.model.Customer;

public class Sale {
	private String code;
	private int quantity;
	private double totPrice;
	private Date saleDate;
	private User customer;
	private boolean paid;
	private List<Ticket> tickets;

	public Sale() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTickets(int i) {
		return tickets.get(i);
	}

	public void setTickets(Ticket ticket) {
		this.tickets.add(ticket);
	}

	public void incompleteSale(String code, int quantity, Ticket ticket) {

		this.code = code;

		this.quantity = quantity;
		int baseQ = 1;
		if (baseQ >= quantity)
			this.quantity = baseQ;

		this.totPrice = ticket.getFlight().getPrice() * this.quantity;

		this.tickets = new ArrayList<>();
		for (int i = 0; i < this.quantity; i++) {
			Ticket nTicket = new Ticket();
			nTicket.cloneTicket(ticket);
			this.tickets.add(nTicket);
		}
	}
	
	public void confirmSale(User customer, String[] names, String[] surnames) {
		
		this.setCustomer(customer);
		
		if(customer instanceof Customer) {
			int point = ((Customer) customer).getPoint();
			((Customer) customer).setPoint(point + (int) this.totPrice);
		}
		
		for(int i = 0; i < this.quantity; i++) {
			Ticket ticket = this.getTickets(i);
			ticket.setSeat(ticket.getFlight().findSeat());
			ticket.setHolderName(names[i]);
			ticket.setHolderSurname(surnames[i]);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try { 
			this.saleDate = sdf.parse(sdf.format(new Date(System.currentTimeMillis()))); 
		} catch (ParseException e) { 
			e.printStackTrace(); 
		} 
		
		this.paid = true;
	}
	
	public void changeSale(int quantity, Ticket ticket, String[] names, String[] surnames) throws Exception {
		
		int baseQ = 1; 
		if (baseQ >= quantity) 
			quantity = baseQ;
		
		double newTotPrice = ticket.getFlight().getPrice() * quantity;
		
		if(this.totPrice > newTotPrice) throw new Exception();
		
		for (int i = 0; i < this.quantity; i++) {
			int seat = this.getTickets(i).getSeat();
			this.getTickets(i).getFlight().freeSeat(seat);;
		}
		
		this.quantity = quantity;
		
		this.totPrice = newTotPrice - this.totPrice;
		
		this.tickets = new ArrayList<>();
		
		for (int i = 0; i < this.quantity; i++) {
			Ticket nTicket = new Ticket();
			nTicket.cloneTicket(ticket);
			nTicket.setSeat(ticket.getFlight().findSeat());
			nTicket.setHolderName(names[i]);
			nTicket.setHolderSurname(surnames[i]);
			this.tickets.add(nTicket);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try { 
			this.saleDate = sdf.parse(sdf.format(new Date(System.currentTimeMillis()))); 
		} catch (ParseException e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void reservation(User customer, String[] names, String[] surnames) {
		
		this.setCustomer(customer);
		
		for(int i = 0; i < this.quantity; i++) {
			Ticket ticket = this.getTickets(i);
			ticket.setSeat(ticket.getFlight().findSeat());
			ticket.setHolderName(names[i]);
			ticket.setHolderSurname(surnames[i]);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try { 
			this.saleDate = sdf.parse(sdf.format(new Date(System.currentTimeMillis()))); 
		} catch (ParseException e) { 
			e.printStackTrace(); 
		} 
	}
	

	@Override
	public String toString() {
		String ticket = "";
		for (int i = 0; i < quantity; i++)
			ticket = ticket + tickets.get(i).toString();
		return "Sale [code=" + code + ", quantity=" + quantity + ", totPrice=" + totPrice + ", saleDate=" + saleDate
				+ ", ticket=" + ticket + "]";
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
		if (!(obj instanceof Sale))
			return false;
		Sale other = (Sale) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
