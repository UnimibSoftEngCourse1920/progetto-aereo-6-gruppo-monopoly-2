package services.ticketsale.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Link;

public class Sale {
	private Ticket ticket;
	private String code;
	private int quantity;
	private double totPrice;
	private Date saleDate;
	private List<Link> links;
	
	public Sale(String code, int quantity, double totPrice, Ticket ticket) {
		super();
		this.ticket = ticket;
		this.code = code;
		this.quantity = quantity;
		this.totPrice = totPrice;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		try {
			this.saleDate = sdf.parse(sdf.format(new Date (System.currentTimeMillis())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Sale [code=" + code + ", quantity=" + quantity + ", totPrice=" + totPrice + ", saleDate=" + saleDate
				+ ", ticket=" + ticket.toString() + "]";
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
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
