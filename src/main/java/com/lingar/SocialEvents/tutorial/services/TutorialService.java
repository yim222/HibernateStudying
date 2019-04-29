package com.lingar.SocialEvents.tutorial.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingar.SocialEvents.tutorial.entities.Car;

@Service
public class TutorialService {
	
	private CarRepository carRepo;
	@Autowired
	public TutorialService (CarRepository carRepo){
		this.carRepo = carRepo;
	}
	
	public void creating10Cars(){
		Calendar cal = Calendar.getInstance();
		cal.set(2008, 5, 11);
		
		carRepo.save(new Car("Avi" , "Honda" , cal.getTime(), 500));
		
	}
	
	
}
