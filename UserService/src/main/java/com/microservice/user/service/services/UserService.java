package com.microservice.user.service.services;

import java.util.List;

import com.microservice.user.service.entities.User;

public interface UserService {
	//user operations
	// create user
	User saveUser(User user);
	
	//get all User
	List<User> getallUser();
	
	//get single user with given id
	User getUser(String userId);
	
	//TODO: delete and update :-> create both
}