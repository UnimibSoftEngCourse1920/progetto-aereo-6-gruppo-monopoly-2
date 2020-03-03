package services.ticketsale.model.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

import services.ticketsale.model.Sale;

public class DtoSale {
	private String code;
	private int quantity;
	private double totPrice;
	private String saleDate;
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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
