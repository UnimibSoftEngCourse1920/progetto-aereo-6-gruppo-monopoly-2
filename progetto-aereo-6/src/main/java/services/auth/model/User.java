package services.auth.model;

import java.util.ArrayList;
import java.util.List;

import services.ticketsale.model.Sale;

public class User {
	private String username;
	private String password;
	private String token;
	private CreditCard creditcard;
	private List<Sale> sales = new ArrayList<>();
	
	public User() {}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public CreditCard getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
	public Sale getSales(int i) {
		return sales.get(i);
	}

	public void setSales(Sale sale) {
		this.sales.add(sale);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", token=" + token + ", creditcard="
				+ creditcard + ", sales=" + sales + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
