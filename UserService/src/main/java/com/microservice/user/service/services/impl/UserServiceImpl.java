package com.microservice.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.microservice.user.service.entities.Hotel;
import com.microservice.user.service.entities.Rating;
import com.microservice.user.service.external.services.HotelService;
import com.microservice.user.service.external.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.user.service.entities.User;
import com.microservice.user.service.exception.ResourceNotFoundException;
import com.microservice.user.service.repositories.UserRepository;
import com.microservice.user.service.services.UserService;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;

	private Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		//generate unique user id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = userRepository.findAll();
		for(User user : users){
			getUser(user.getUserId());
		}
		return users;
	}

	@Override
	public User getUser(String userId) {
		logger.info("{}", userId);
		User user = new User();
		user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! : " + userId));
		//fetch rating of above user from RATING-SERVICE
		// user rating url=
		//we should have client to call other service i.e. RestTemplate
		//Rating[] ratingForUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class);

		//using Feign client we are calling request
		List<Rating> listRating = ratingService.getRatingByUserId(user.getUserId()).getBody();
		//List<Rating> listRating = Arrays.stream(ratingForUser).toList();

		logger.info("{}", listRating);

		List<Rating> ratingList = listRating.stream().map(rating -> {
			//api call to hotel service to get hotel with hotel id in ratingForUser object
			// set hotel to rating
			//return rating
			/*ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelEntity.getBody();*/
			/*ResponseEntity<Hotel> hotelEntity = hotelService.getHotelById(rating.getHotelId());
			Hotel hotel = hotelEntity.getBody();*/
			//one Liner
			Hotel hotel = hotelService.getHotelById(rating.getHotelId()).getBody();
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}
}
