package services.customer.model.dto;

import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

import services.auth.model.User;
import services.customer.model.Customer;

public class DtoCustomer {
	private String username;
	private String name;
	private String surname;
	private String email;
	private int point;
	private String[] sales;
	@JsonIgnore
	private List<Link> links;
	
	public DtoCustomer() {}
	
	public void createDtoCustomer(Customer customer) {
		this.username = customer.getUsername();
		this.name = customer.getName();
		this.surname = customer.getSurname();
		this.email = customer.getEmail();
		this.point = customer.getPoint();
		
		sales = new String[customer.getSales().size()];
		System.out.println(sales.length);
		for(int i = 0; i < customer.getSales().size(); i++)
			sales[i] = customer.getSales(i).getCode();
	}
	
	public void createDtoCustomer(User customer) {
		this.username = customer.getUsername();
		this.name = "";
		this.surname = "";
		this.email = "";
		this.point = 0;
		
		sales = new String[customer.getSales().size()];
		System.out.println(sales.length);
		for(int i = 0; i < customer.getSales().size(); i++)
			sales[i] = customer.getSales(i).getCode();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String[] getSales() {
		return sales;
	}

	public void setSales(String[] sales) {
		this.sales = sales;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
