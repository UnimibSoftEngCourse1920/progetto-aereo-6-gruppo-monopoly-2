package services.customer.model.dto;

import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DtoCustomer {
	private String username;
	private String name;
	private String surname;
	private String email;
	private int point;
	@JsonIgnore
	private List<Link> links;
	
	public DtoCustomer() {}
	
	public void newCustomer(String username, String name, String surname, String email, int point) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.point = point;
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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
