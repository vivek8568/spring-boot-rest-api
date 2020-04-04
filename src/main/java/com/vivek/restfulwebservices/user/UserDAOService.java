package com.vivek.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	private static ArrayList<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1, "vivek", new Date()));
		users.add(new User(2, "vicky", new Date()));
		users.add(new User(3, "adam", new Date()));
	}
	
	private static int userCount = 3;
	
	public ArrayList<User> getAll() {
		return users;
	}
	
	public User getById(int id) {
		for (User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User createUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User deleteUserById(int id) {
		Iterator<User> iterator =  users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
