package com.lingar.SocialEvents.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingar.SocialEvents.entities.MultiPropValue;
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
	
	@Autowired//this is how to insert other spring component (without the necessary to instantiate that 
	EntitiesService entitiesService;
	
	
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
	public String showArray(@RequestParam  String[] array){//The names must be equals at the path param and Java param
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
	/**
	 * Good URL - http://localhost:8080/ws/filter-try1?eventType=meeting&eventType=vaacation&eventType=games&jewLvlKeep=shabbat&jewLvlKeep=noShabbat&area=north&area=south&agesRange=0&agesRange=120&datesRange=2019-12-07&datesRange=2020-09-08
	 */
	//trying to make filter
	//*** - U have a file with ready URL paths for that. 
	@RequestMapping("/filter-try1")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
//	public String filterTry1(@RequestParam  String[] eventType){//The names must be equals at the path param and Java param
	public  List <SocialEvent> filterTry1(@RequestParam  List<String> eventType,
			@RequestParam  List<String> jewLvlKeep,
			@RequestParam  List<String> area,
			@RequestParam  int[] agesRange,
			@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] datesRange 
			){//The names must be equals at the path param and Java param
		//U can pass those who is in the file of the URL calls 
		
		entitiesService.test1();
		
		//comment from the filter : Note to pass the date at the edge of the day (start and end) in hours aspect, for not miss things.  
		//But maybe u can tell it in the query (that betwenn include the day but apparently it's not looking on the 
		//day but on the hour. SO u need to define "to" with 59. Or that the hours doesn't includes, but I thing that does . Let's look  -
		//TODO - Answer - currently it's take the hour of the creating time - so when U will work on the creating, 
		// U will define it to be at 23:59. Or maybe the accurate hour. - TODO in the future. 
		
		
		
		//convert eventTypes to List of MultiValues 
		Map <String, List<String> > eventTypeValues = new TreeMap<String, List<String>>();
		eventTypeValues.put("eventType" , eventType);
		Set<MultiPropValue> eventTypeMap = entitiesService.generateMultiValuesList(eventTypeValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(eventTypeMap );
		
		//convert jewLvlKeeps to List of MultiValues 
		Map <String, List<String> > jewLvlKeepValues = new TreeMap<String, List<String>>();
		jewLvlKeepValues.put("jewLvlKeep" , jewLvlKeep);
		Set<MultiPropValue> jewLvlKeepMap = entitiesService.generateMultiValuesList(jewLvlKeepValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(jewLvlKeepMap );
		
		//convert areas to List of MultiValues 
		Map <String, List<String> > areaValues = new TreeMap<String, List<String>>();
		areaValues.put("area" , area);
		Set<MultiPropValue> areaMap = entitiesService.generateMultiValuesList(areaValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(areaMap );
		System.out.println("Original area = "+ area);
		
		
		
		
		
		
		List <SocialEvent> resultEvents = entitiesService.socialEventRepository.filter16Main(
				new ArrayList<>(eventTypeMap),
				new ArrayList<>(areaMap),
				new ArrayList<>(jewLvlKeepMap),				
				agesRange[0], agesRange[1],
				datesRange[0], datesRange[1]);
		System.out.println("resultEvents  = \n" +  resultEvents);//U made socialEventRepo public maybe it's not right. Do something else . 
		
		entitiesService.displayEventsShort(resultEvents);
		return resultEvents;
		/*
		 * List<SocialEvent> filter16Main(
			List <MultiPropValue> eventTypes,
			List <MultiPropValue> areas, List <MultiPropValue> jewLvlKeep,
			int fromAge, int toAge,
			Date from , Date to);
		 */
	}
	
	@RequestMapping("/simpleString")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
	public String testPathParams(@RequestParam 
			@PathParam("x")  String x){
//		http://localhost:8080/ws/filter-try1?simpleString=gg
		
		
		return "x = " + x;
	}
	
	@RequestMapping("/forTrying")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
	public String forTrying(@RequestParam 
			@PathParam("x") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date x){
		//		http://localhost:8080/ws/forTrying?x==2019-12-07
		System.out.println("x = " + x);
		
		
		return "x = " + x;
	}
	
	
	
}
