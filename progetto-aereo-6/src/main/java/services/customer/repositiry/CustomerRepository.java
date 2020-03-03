package services.customer.repositiry;

import java.util.HashMap;
import java.util.Map;

import services.auth.model.User;
import services.customer.model.Customer;

public class CustomerRepository {
	private Map<String, Customer> customers;
	private static CustomerRepository instance = null;
		
	public static CustomerRepository getInstance() {
		if (instance == null) {
			instance = new CustomerRepository();
		}
		return instance;
	}
		
	private CustomerRepository() {
		this.customers = new HashMap<>();
		this.customers.put("pietroanelli", new Customer(new User("pietroanelli", "password"), "Pietro", "Anelli"));
	}
		
	public Customer find(String username) {
		return this.customers.get(username);
	}
}

