package com.rating.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.service.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
	//custom finder
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
