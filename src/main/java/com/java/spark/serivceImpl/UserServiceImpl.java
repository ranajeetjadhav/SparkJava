package com.java.spark.serivceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.java.spark.entity.User;
import com.java.spark.exception.UserException;
import com.java.spark.serivce.UserService;

public class UserServiceImpl implements UserService {

	Map<String, User> userMap = new LinkedHashMap<>();
	
	public UserServiceImpl() {	
		userMap.put("101", new User("101", "Ranajeet", "Jadhav", "ranajeet@gmail.com"));
		userMap.put("102", new User("101", "Avinash", "More", "avinash@gmail.com"));
	}

	@Override
	public void addUser(User user) {
		userMap.put(user.getId(), new User(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail()));
	}

	@Override
	public List<User> getUsers() {
		List<User> userList = new ArrayList<>(userMap.values());
		return userList;
	}

	@Override
	public User getUser(String id) {
		User user = null; 
		
		if(userMap.containsKey(id)){
			user = userMap.get(id);
		}
		
	    return user; 
	}
	
	@Override
	public User editUser(User user) throws UserException {
		return null;
	}

	@Override
	public void deleteUser(String id) {

		if(userMap.containsKey(id)){
			userMap.remove(id);
		}
	}

	@Override
	public boolean isUserExist(String id) {
		if(userMap.containsKey(id)){
			return true;
		}
		return false;
	}

}
