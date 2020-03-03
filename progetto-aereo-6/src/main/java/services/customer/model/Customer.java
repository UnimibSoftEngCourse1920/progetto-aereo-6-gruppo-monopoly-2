package services.customer.model;

import services.auth.model.User;

public class Customer {
	private User self;
	private String name;
	private String surname;
	private CreditCard creditcard;
	
	public Customer(User self, String name, String surname) {
		super();
		this.self = self;
		this.name = name;
		this.surname = surname;
	}
	
	public User getSelf() {
		return self;
	}

	public void setSelf(User self) {
		this.self = self;
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

	public CreditCard getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}
}
