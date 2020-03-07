package services.customer.model;

import services.auth.model.User;

public class Customer extends User {
	private String name;
	private String surname;
	private String email;
	private int point;
	private CreditCard creditcard;
	
	public Customer() {}
	
	public void subscribe(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.point = 0;
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
	
	public CreditCard getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + ", email=" + email + ", point=" + point
				+ ", creditcard=" + creditcard + ", toString()=" + super.toString() + "]";
	}
}
