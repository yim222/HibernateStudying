package com.lingar.SocialEvents.tutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lingar.SocialEvents.services.MultiPropValueRepository;

@Component
public class TutorialRunning implements CommandLineRunner {

	private CarRepository carRepo;
	private MultiPropValueRepository multiValueRepo;
	@Autowired
	public TutorialRunning(CarRepository carRepo,MultiPropValueRepository multiValueRepo ){
		
		this.carRepo = carRepo;
		this.multiValueRepo = multiValueRepo;
	}
	
	@Override
	public void run(String ...args ) throws Exception{
		
		System.out.println("Tutorial running...");
		
		System.out.println( "\n**************************************************\nU can learn from here many things \n "
				+ "***********************************************\n" );
		
		TutorialService tService = new TutorialService(carRepo);
		tService.creating10Cars(); // -> In each running create initial data 
		tService.studyingPreDefinedQueries();
		tService.studyingQueries1();
		tService.studyingQueries2();
		tService.studyingQueries3();
		
		
		
		
		
	}
	
}
