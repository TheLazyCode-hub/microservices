package com.rating.service.services;

import java.util.List;

import com.rating.service.entities.Rating;

public interface RatingService {
	//create
	public Rating create(Rating rating);
	
	// get all
	public List<Rating> getAll();
	
	//get by user id
	public List<Rating> getAllByUserId(String userId);
	
	//get by hotel id
	public List<Rating> getAllByHotelId(String hotelId);
	
}
