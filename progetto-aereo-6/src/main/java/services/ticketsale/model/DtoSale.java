package services.ticketsale.model;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DtoSale {
	private String code;
	private int quantity;
	private double totPrice;
	private String saleDate;
	private boolean paid;
	@JsonIgnore
	private List<Link> links;
	
	public DtoSale() {}
	
	public DtoSale(Sale sale) {
		super();
		this.code = sale.getCode();
		this.quantity=sale.getQuantity();
		this.totPrice=sale.getTotPrice();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		this.saleDate = sdf.format(sale.getSaleDate());
		this.setPaid(sale.isPaid());
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

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	
	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
