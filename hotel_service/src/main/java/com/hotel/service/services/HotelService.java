package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entities.Hotel;

public interface HotelService {
	//create 
	Hotel create(Hotel hotel);
	//get all
	List<Hotel> getAll();
	//get by id
	Hotel getById(String id);
}
