package com.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entities.Rating;
import com.rating.service.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	public RatingService ratingService;
	
	
	//create
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	//get all
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAll());
	}
	
	//get by user id
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getAllByUserId(@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAllByUserId(userId));
	}
	
	//get by hotel id
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getAllByHotelId(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAllByHotelId(hotelId));
	}
}
