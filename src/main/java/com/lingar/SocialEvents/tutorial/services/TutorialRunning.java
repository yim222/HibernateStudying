package com.lingar.SocialEvents.tutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TutorialRunning implements CommandLineRunner {

	private CarRepository carRepo;
	
	@Autowired
	public TutorialRunning(CarRepository carRepo){
		
		this.carRepo = carRepo;
	}
	
	@Override
	public void run(String ...args ) throws Exception{
		
		System.out.println("Tutorial running...");
		
		
	}
	
}
