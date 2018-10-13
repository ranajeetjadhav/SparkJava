/**
 * 
 */
package com.java.spark.serivce;

import java.util.List;

import com.java.spark.entity.User;
import com.java.spark.exception.UserException;

/**
 * @author E1077874
 *
 */
public interface UserService {

	public void addUser(User user);
	
	public List<User> getUsers();
	
	public User getUser(final String id);
	
	public User editUser(User user) throws UserException;
	
	public void deleteUser(final String id);
	
	public boolean isUserExist(String id);
	
}
