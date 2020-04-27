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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingar.SocialEvents.dataObjects.SocialEventData;
import com.lingar.SocialEvents.dataObjects.TestData;
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
@CrossOrigin
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
		System.out.println("Get events greater then today");
		Date date;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);//set time to 00:00 - the day beginning
		cal.set(Calendar.MINUTE, 0);
		date = cal.getTime();
		List<SocialEvent> events; //= socialEventRepo.findByDateGreaterThanEqualOrderByDateAsc(date);


		try {
			events = socialEventRepo.findByDateGreaterThanEqualOrderByDateAsc(date);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("There is error getevents - " + e.getMessage());
			return null;
		}
		System.out.println("Events = ");
		entitiesService.displayEventsShort(events);

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
		//day but on the hour. SO u need to define "to" with 59. Or that the hours doesn't includes, but I think that does . Let's look  -
		//TODO - Answer - currently it's take the hour of the creating time - so when U will work on the creating, 
		// U will define it to be at 23:59. Or maybe the accurate hour. - TODO in the future. 
		
		
		
		//convert eventTypes to List of MultiValues 
		Map <String, List<String> > eventTypeValues = new TreeMap<String, List<String>>();
//		if(eventType.size()<1){//TODO - that's one direction - with adding global value. This value will be need to assign to all event so it is not so good.
////			eventTypeParams.add(null);	
//			eventType.add("all");
//			
//		}
		eventTypeValues.put("eventType" , eventType);
		Set<MultiPropValue> eventTypeMap = entitiesService.generateMultiValuesList(eventTypeValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(eventTypeMap );
		//Convert Set to List Prepare the values to MultiPropValue List - for pass to the filter
		List<MultiPropValue> eventTypeParams = new ArrayList<>(eventTypeMap);
		//checking if it empty 
		if(eventType.size()<1){
//			eventTypeParams.add(null);	
			MultiPropValue m = new MultiPropValue("xx", "xxx");
			eventTypeParams.add(null);
			//eventTypeParams = null;
			
		}
		
		//convert areas to List of MultiValues 
		Map <String, List<String> > areaValues = new TreeMap<String, List<String>>();
		areaValues.put("area" , area);
		Set<MultiPropValue> areaMap = entitiesService.generateMultiValuesList(areaValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(areaMap );
		System.out.println("Original area = "+ area + " size? " + area.size());
		
		List<MultiPropValue> areaParams = new ArrayList<>(areaMap);
		System.out.println("area.size ? " +area.size() );

		if(area.size()<1){
			//areaParams.add("");
			System.out.println("make params null" );
//					areaParams = null;
			areaParams.add(null);
			
//					System.out.println("make params with flag" );
//
//					areaParams.add("all");

		}
		
		//convert jewLvlKeeps to List of MultiValues 
		Map <String, List<String> > jewLvlKeepValues = new TreeMap<String, List<String>>();
		jewLvlKeepValues.put("jewLvlKeep" , jewLvlKeep);
		Set<MultiPropValue> jewLvlKeepMap = entitiesService.generateMultiValuesList(jewLvlKeepValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(jewLvlKeepMap );
//		List<MultiPropValue> jewLvlKeepParams = new ArrayList<>(eventTypeMap);//this was the mistake. With simple log U can find it
		List<MultiPropValue> jewLvlKeepParams = new ArrayList<>(jewLvlKeepMap);
		//checking if it empty 
		if(jewLvlKeep.size()<1){
			jewLvlKeepParams.add(null);	
		}
		
		
		
		System.out.println("eventTypeParams before sending = " + eventTypeParams);
		
		System.out.println("Keeplvl before sending = " + jewLvlKeepParams);

		
		
		
		System.out.println("areaParams before sending " +areaParams );
		
		/*
		 * TODO
		 * 1- Make handling to eventTypes and Areas and JewLvl
		 * 2- Make excepted logs : 
		 * Getting filter query : 
		 * Event type: the list
		 * Jew Level Keeping: The list
		 * Areas: The list 
		 * From To ages : 1 to 120
		 * From date : ...
		 * To date : 
		 * 
		 * The Results : with display short. 
		 * 
		 * U NEED TO TEST ALL THIS 
		 * 
		 */
		 /*TODO FUTUTRE -  make handling empty values by the query. See the doc that saved. at archive - empty-values-query*/
		
		
		
		
		List <SocialEvent> resultEvents = entitiesService.socialEventRepository.filter16Main(
				eventTypeParams,
				areaParams,
				jewLvlKeepParams,				
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
	
	
	@RequestMapping("/filter-try2")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
//	public String filterTry1(@RequestParam  String[] eventType){//The names must be equals at the path param and Java param
	public  List <SocialEvent> filterTry2(@RequestParam  List<String> eventType,
			@RequestParam  List<String> jewLvlKeep,
			@RequestParam  List<String> area,
			@RequestParam  int[] agesRange,
			@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] datesRange 
			){//The names must be equals at the path param and Java param
		//U can pass those who is in the file of the URL calls 
		int eventTypesMissing = 0;
		entitiesService.test1();
		
		//comment from the filter : Note to pass the date at the edge of the day (start and end) in hours aspect, for not miss things.  
		//But maybe u can tell it in the query (that betwenn include the day but apparently it's not looking on the 
		//day but on the hour. SO u need to define "to" with 59. Or that the hours doesn't includes, but I thing that does . Let's look  -
		//TODO - Answer - currently it's take the hour of the creating time - so when U will work on the creating, 
		// U will define it to be at 23:59. Or maybe the accurate hour. - TODO in the future. 
		
		
		
		//convert eventTypes to List of MultiValues lingar work here
		Map <String, List<String> > eventTypeValues = new TreeMap<String, List<String>>();
		if(eventType.size()<1){//TODO - that's one direction - with adding global value. This value will be need to assign to all event so it is not so good.
////			eventTypeParams.add(null);	
			eventType.add("all");
			eventTypesMissing = 1;
//			
		}
		eventTypeValues.put("eventType" , eventType);
		Set<MultiPropValue> eventTypeMap = entitiesService.generateMultiValuesList(eventTypeValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(eventTypeMap );
		//Convert Set to List Prepare the values to MultiPropValue List - for pass to the filter
		List<MultiPropValue> eventTypeParams = new ArrayList<>(eventTypeMap);
		//checking if it empty handling with null... Not suppose to affect but... 
//		if(eventTypes.size()<1){
////			eventTypeParams.add(null);	
//			MultiPropValue m = new MultiPropValue("xx", "xxx");
//			eventTypeParams.add(null);
////			eventTypeParams = null;
//			
//		}
		
		//convert areas to List of MultiValues 
		Map <String, List<String> > areaValues = new TreeMap<String, List<String>>();
		areaValues.put("area" , area);
		Set<MultiPropValue> areaMap = entitiesService.generateMultiValuesList(areaValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(areaMap );
		System.out.println("Original area = "+ area + " size? " + area.size());
		
		List<MultiPropValue> areaParams = new ArrayList<>(areaMap);
		System.out.println("area.size ? " +area.size() );

		if(area.size()<1){
			//areaParams.add("");
			System.out.println("make params null" );
//					areaParams = null;
			areaParams.add(null);
			
//					System.out.println("make params with flag" );
//
//					areaParams.add("all");

		}
		
		//convert jewLvlKeeps to List of MultiValues 
		Map <String, List<String> > jewLvlKeepValues = new TreeMap<String, List<String>>();
		jewLvlKeepValues.put("jewLvlKeep" , jewLvlKeep);
		Set<MultiPropValue> jewLvlKeepMap = entitiesService.generateMultiValuesList(jewLvlKeepValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(jewLvlKeepMap );
		List<MultiPropValue> jewLvlKeepParams = new ArrayList<>(eventTypeMap);
		//checking if it empty 
		if(jewLvlKeep.size()<1){
			jewLvlKeepParams.add(null);	
		}
		
		
		
		System.out.println("eventTypeParams before sending = " + eventTypeParams);
		
		System.out.println("jewLvl before sending = " + jewLvlKeepParams);

		
		System.out.println("areaParams before sending " +areaParams );
		
		/*
		 * TODO
		 * 1- Make handling to eventTypes and Areas and JewLvl
		 * 2- Make excepted logs : 
		 * Getting filter query : 
		 * Event type: the list
		 * Jew Level Keeping: The list
		 * Areas: The list 
		 * From To ages : 1 to 120
		 * From date : ...
		 * To date : 
		 * 
		 * The Results : with display short. 
		 * 
		 * U NEED TO TEST ALL THIS 
		 * 
		 */
		 /*TODO FUTUTRE -  make handling empty values by the query. See the doc that saved. at archive - empty-values-query*/
		
		
		System.out.println("eventTypesMissing ?  " + eventTypesMissing);
		System.out.println("The filter:");
		List <SocialEvent> resultEvents = entitiesService.socialEventRepository.filter17Try(
				eventTypeParams,
				areaParams,
				jewLvlKeepParams,				
				agesRange[0], agesRange[1],
				datesRange[0], datesRange[1],
				eventTypesMissing);
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

	/**
	 * Good URL - http://localhost:8080/ws/filter-try3?eventType=meeting&eventType=vaacation&eventType=games&jewLvlKeep=shabbat&jewLvlKeep=noShabbat&area=north&area=south&agesRange=0&agesRange=120&datesRange=2019-12-07&datesRange=2020-09-08
	 */
	//trying to make filter
	//*** - U have a file with ready URL paths for that. 
	@RequestMapping("/filter-try3")//passing arrays - https://medium.com/@AADota/spring-passing-list-and-array-of-values-as-url-parameters-1ed9bbdf0cb2
//	public String filterTry1(@RequestParam  String[] eventType){//The names must be equals at the path param and Java param
	public  List <SocialEvent> filterTry3(
			@RequestParam  List<String> eventType,
			@RequestParam  List<String> jewLvlKeep,
			@RequestParam  List<String> area,
			@RequestParam  int[] agesRange,
			@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] datesRange 
			){//The names must be equals at the path param and Java param
			//U can pass those who is in the file of the URL calls 
		
		entitiesService.test1();
		
		
		//convert eventTypes to List of MultiValues 
		Map <String, List<String> > eventTypeValues = new TreeMap<String, List<String>>();
		eventTypeValues.put("eventType" , eventType);
		Set<MultiPropValue> eventTypeMap = entitiesService.generateMultiValuesList(eventTypeValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(eventTypeMap );
		//Convert Set to List Prepare the values to MultiPropValue List - for pass to the filter
		List<MultiPropValue> eventTypeParams = new ArrayList<>(eventTypeMap);
		//checking if it empty 
		if(eventType.size()<1){
			eventTypeParams.add(null);	
						
		}
		
		//convert areas to List of MultiValues 
		Map <String, List<String> > areaValues = new TreeMap<String, List<String>>();
		areaValues.put("area" , area);
		Set<MultiPropValue> areaMap = entitiesService.generateMultiValuesList(areaValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(areaMap );
		System.out.println("Original area = "+ area + " size? " + area.size());
		
		List<MultiPropValue> areaParams = new ArrayList<>(areaMap);
		System.out.println("area.size ? " +area.size() );

		if(area.size()<1){			
			areaParams.add(null);
		}
		
		//convert jewLvlKeeps to List of MultiValues 
		Map <String, List<String> > jewLvlKeepValues = new TreeMap<String, List<String>>();
		jewLvlKeepValues.put("jewLvlKeep" , jewLvlKeep);
		Set<MultiPropValue> jewLvlKeepMap = entitiesService.generateMultiValuesList(jewLvlKeepValues);//TODO future - why it's return set ? is the filter get it ? 
		System.out.println(jewLvlKeepMap );
		List<MultiPropValue> jewLvlKeepParams = new ArrayList<>(eventTypeMap);
		//checking if it empty 
		if(jewLvlKeep.size()<1){
			jewLvlKeepParams.add(null);	
		}	
		System.out.println("eventTypeParams before sending = " + eventTypeParams);
		System.out.println("areaParams before sending " +areaParams );		
		List <SocialEvent> resultEvents = entitiesService.socialEventRepository.filter18Try(
				eventTypeParams,
				areaParams,
				jewLvlKeepParams,				
				agesRange[0], agesRange[1],
				datesRange[0], datesRange[1]);
		System.out.println("resultEvents  = \n" +  resultEvents);//U made socialEventRepo public maybe it's not right. Do something else . 
		
		entitiesService.displayEventsShort(resultEvents);
		return resultEvents;		
	}
	/**
	 * OLD
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/createSocialEventold1", method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN_VALUE)
	public boolean createSocialEventOld1(@RequestBody String str){
		System.out.println("Data = " + str);
		
		return true;
	}
	
	
	@RequestMapping(value = "/createSocialEvent", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
			 )
	public boolean createSocialEvent(@RequestBody SocialEventData data){//was List<String>
		System.out.println("Data = " + data);
		System.out.println(data.getMultiValues().get("eventType"));
//		System.out.println(data.get("c"));
		
		//entitiesService.createSocialEvent(data.getSingleValues(), data.getMultiValues(), data.getComment());
		entitiesService.createSocialEvent(data.getSingleValues(), data.getMultiValues() , data.getComment(), data.getDate(),
				data.getTime(), data.getAgesRange());

		
		
		return true;
	}
	
	@RequestMapping(value = "/testPost", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
			 )
	public boolean testPost(@RequestBody TestData data){
		System.out.println("Data = " + data);
		System.out.println("Data nested  = " + data.getMyvalues().get("key1") ) ;
		
		
		
		return true;
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
		//		http://localhost:8080/ws/forTrying?x=2019-12-07
		System.out.println("x = " + x);
		
		
		return "x = " + x;
	}
	
	
	
}
