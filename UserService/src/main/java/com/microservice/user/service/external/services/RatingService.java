package com.microservice.user.service.external.services;

import com.microservice.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @GetMapping("/rating/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId);
}
