package com.lingar.SocialEvents.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingar.SocialEvents.entities.SocialEvent;
import com.lingar.SocialEvents.services.EntitiesService;
import com.lingar.SocialEvents.services.SocialEventRepository;
/**
 * Controller for creating the desire requests. 
 * @author lingar
 *
 */
@RestController
@RequestMapping("/ws")
public class WebServices {
	
	@Autowired
	SocialEventRepository socialEventRepo;
	//EntitiesService entitiesService;
	
	@RequestMapping("/getEvents")
	//http://localhost:8080/ws/getEvents
	//Get all events that greater or equal then today  
	public List<SocialEvent> getEvents(){
		
		Date date;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);//set time to 00:00 - the day beginning
		cal.set(Calendar.MINUTE, 0);
		date = cal.getTime();
		List<SocialEvent> events = socialEventRepo.findByDateGreaterThanEqualOrderByDateAsc(date);

		return events;
	}
	
}
