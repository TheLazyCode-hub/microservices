package com.microservice.user.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.user.service.entities.User;
import com.microservice.user.service.exception.ResourceNotFoundException;
import com.microservice.user.service.repositories.UserRepository;
import com.microservice.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		//generate unique user id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getallUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
			return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server!! : "+userId));
	}

}
