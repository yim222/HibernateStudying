package com.lingar.SocialEvents.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingar.SocialEvents.entities.SocialEvent;
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
	
	@RequestMapping("/test1")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
	public String showArray(@RequestParam  String[] array){
		//U can pass those : 
		//http://localhost:8080/ws/test1?array=gg,kk,sd
		//	http://localhost:8080/ws/test1?array=gg&array=kk&array=sd
		
		return array[1];
	}
	
	//http://localhost:8080/ws/testPathParams?x=Hello&y=WhatsUp&a=5&b=8
	@RequestMapping("/testPathParams")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
	public String testPathParams(@RequestParam 
			@PathParam("x")  String x, 
			@PathParam("y")  String y, 
			@PathParam("a")  int a,
			@PathParam("b")  int b){
		String msg = "a = " + a + " b =  " + b + " sum = " + (a+b);
		
		
		return "x = " + x + " y = " + y + "\n"+msg;
	}
	
	//testPathParams - example found the way of the week by the month day (and starting day of the month) 
	//http://localhost:8080/ws/weekBymonthDay?startingMonthDay=4&monthDay=23
	@RequestMapping("/weekBymonthDay")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
	public int weekBymonthDay(@RequestParam 
			@PathParam("startingMonthDay")  int startingMonthDay, 
			@PathParam("monthDay")  int monthDay){
		
		int all =  startingMonthDay-1 + monthDay;		
		int firstShabbatMd = 7 - (startingMonthDay -1 );
		int range = monthDay - firstShabbatMd;
		int weekDay = range % 7 > 0 ? range % 7 : 7;
		System.out.println("month day = " + monthDay + " | starting day = "  + startingMonthDay 
				+" ||First shabbat Md = "+ firstShabbatMd +  " | Week day = " + weekDay);
		
		return weekDay;
		
	}
	//http://localhost:8080/ws/basicTest
	@RequestMapping("/basicTest")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
	public String basicWsTest(){
		
		System.out.println("test of the web Service");
		return "this is basic ws test";
	}
	
	
}
