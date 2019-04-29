package com.lingar.SocialEvents.tutorial.services;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.tutorial.entities.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{

	
	
	
}
