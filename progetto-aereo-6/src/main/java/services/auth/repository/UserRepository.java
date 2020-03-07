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
	}
		
	public User find(String username) {
		return this.users.get(username);
	}
	
	public void save(User user) {
		this.users.put(user.getUsername(), user);
	}
	
	public void update(String username, User user) {
	    this.users.replace(username, user);
	}
}
	

