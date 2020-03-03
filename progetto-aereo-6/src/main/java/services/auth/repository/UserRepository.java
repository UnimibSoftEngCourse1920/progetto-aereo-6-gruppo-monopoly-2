package services.auth.repository;

import java.util.HashMap;
import java.util.Map;

import services.auth.model.User;

public class UserRepository {
	private Map<String, User> users;
	private static UserRepository instance = null;
		
	public static UserRepository getInstance() {
		if (instance == null) {
			instance = new UserRepository();
		}
		return instance;
	}
		
	private UserRepository() {
		this.users = new HashMap<>();
		this.users.put("pietroanelli", new User("pietroanelli", "password"));
	}
		
	public User find(String username) {
		return this.users.get(username);
	}
}
	

