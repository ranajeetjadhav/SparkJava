package com.java.spark.contoller;

import com.google.gson.Gson;
import com.java.spark.entity.User;
import com.java.spark.response.StandardResponse;
import com.java.spark.response.StatusResponse;
import com.java.spark.serivce.UserService;
import static spark.Spark.*;
import com.java.spark.serivceImpl.UserServiceImpl;

public class UserServiceController {

	private static UserService userService = new UserServiceImpl();
	
	public static void main(String[] args) {
		
		// get all users
		get("/users", (request, response) -> {
			response.type("application/json");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(userService.getUsers())));
			
		});
		
		// get user by id
		get("/users/:id", (request, response) -> {
			response.type("application/json");
			User user = userService.getUser(request.params(":id"));
			
			if(user != null){
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJson(user)));
			}else{
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "User not found"));
			}
		});
		
		// create user
		post("/users", (request, response) -> {
			response.type("application/json");
			User user = new Gson().fromJson(request.body(), User.class);
			userService.addUser(user);
			
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
		});
		
		// update user
		put("/users/:id", (request, response) -> {
			response.type("application/json");
			User toEdituser = new Gson().fromJson(request.body(), User.class);
			User editedUser = userService.editUser(toEdituser);
			
			if(editedUser!= null){
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "User updated successfully"));
			}else{
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "User not found or error in update"));
			}
			
		});
		
		// delete user
		delete("/users/:id", (request, response) -> {
			response.type("application/json");
			userService.deleteUser(request.params(":id"));
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "User deleted successfully"));
		});
		
		// check whether existing user
		options("/users/:id", (request, response) -> {
			response.type("application/json");
			
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, (userService.isUserExist(request.params(":id"))) ? "User exist":"User does not exist"));
		});
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
