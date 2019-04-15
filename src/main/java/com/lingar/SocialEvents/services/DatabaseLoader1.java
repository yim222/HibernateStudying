package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lingar.SocialEvents.entities.MultiPropName;
import com.lingar.SocialEvents.entities.MultiPropValue;
import com.lingar.SocialEvents.entities.SinglePropName;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;



@Component//It's will check it becaue the classPath ? 
public class DatabaseLoader1 implements CommandLineRunner {
	
	
	
	
	private final SocialEventRepository socialEventRepository;
	private final SinglePropNameRepository singlePropNameRepository;
	private final SinglePropValueRepository singlePropValueRepository;
	private final MultiPropNameRepository multiPropNameRepository;
	private final MultiPropValueRepository multiPropValueRepository;
	
	
	//First get the repository2 of the CRUD
	@Autowired
	public DatabaseLoader1(SocialEventRepository repository22,
			SinglePropNameRepository singlePropNameRepository,
			SinglePropValueRepository singlePropValueRepository,
			MultiPropNameRepository multiPropNameRepository,
			MultiPropValueRepository multiPropValueRepository
			
			
			) {
		//We create Repositoris to each entity - if U want a full control on the using. 
		this.socialEventRepository = repository22;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		this.multiPropNameRepository = multiPropNameRepository;
		this.multiPropValueRepository = multiPropValueRepository;
		
	}

	//Then Do with that saving. 
	@Override
	public void run(String... strings) throws Exception {
		
		/**
		
		//We create new SinglePropName- U need to change it in each running because it's defined "Unique" and duplicates are not allowed
		SinglePropName singlePropName1 = new SinglePropName("other10");
		
		//We save it with the Repository
		singlePropNameRepository.save(singlePropName1);
		
		//The same about SinglePropValue -  but here : 
		//1 - U don't need to change it - it's not duplicate . 
		//2- U must to save the SinglePropName before pass it to this entity. If not - U'll 
		//get error of "unsaved object" . U can handle it from inside with @casade type all and like that 
		//but u lose a controling on that, and in you complicating issues it not work. 
		SinglePropValue singlePropValue1 = new SinglePropValue(singlePropName1, "lingar44" );
		SinglePropValue singlePropValue2 = new SinglePropValue(singlePropName1, "Yosisss" ); 
		
		
		
		//Savine the value-props
		singlePropValueRepository.save(singlePropValue1);
		singlePropValueRepository.save(singlePropValue2);
		
		
		//https://stackoverflow.com/a/50186140/9727918
		
		//If U want to use an exist entity to new object (I want the "name" singlePropName for some differents values)
		//U need to get it from the db in this way 
		//Note: 1- maybe there are better ways. 2- This Id's is from my current DB. Maybe U'll need to change it 
		//if U use other or reset DB. so run it in "update" mode , generate propnames and then insert the right values
		
		//Get the data as optional
		Optional <SinglePropName> dbData1 =  singlePropNameRepository.findById(4L);
		
		//asssign it to property . 
		SinglePropName singlePropName3 = dbData1.get();
		System.out.println(singlePropName3.getPropName());
		
		//Generate from that new value.
		SinglePropValue singlePropValue3 = new SinglePropValue(singlePropName3, "New event" );
		
		//save it 
		singlePropValueRepository.save(singlePropValue3);
		
		//generate an List that will contain the values.  For pass it to the Social event
		List<SinglePropValue> singleValuesList = new ArrayList<>();
		
		//Generate two more object for insert to the list 
		
		//It's the one of the name 
		SinglePropValue singlePropValueEl1 = new SinglePropValue(singlePropName3, "Trip" );
		
		//get othere propName (address ? ) 
		dbData1 =  singlePropNameRepository.findById(1L);
		SinglePropName singlePropName4 = dbData1.get();
		
		//Generate with that new object 
		SinglePropValue singlePropValueEl2 = new SinglePropValue(singlePropName4, "Holon" );
		
		//save them both - other wise it will make problem when passing it to the Social event
		singlePropValueRepository.save(singlePropValueEl1);singlePropValueRepository.save(singlePropValueEl2);
		
		//Add values to the list 
		singleValuesList.add(singlePropValueEl1);
		singleValuesList.add(singlePropValueEl2);
		
		//creating new socEvent
		//SocialEvent(String description, String moreValue, int fromAge, int toAge, List<SinglePropValue> singleValuesList)
		SocialEvent socialEvent1 = new SocialEvent("desc","bla", 3, 4,  singleValuesList);
		
		//save it 
		repository2.save(socialEvent1);
		//Check the db for seeing that.

		
		
		
		*/
		System.out.println("Lesson 3. ");
		System.out.println("Trying to Use EntitiesService");
		
		EntitiesService entitiesService = new EntitiesService(socialEventRepository,
				singlePropNameRepository,
				singlePropValueRepository);
		
		
		System.out.println("Trying to create Initial Data \n It's to run just once So I am comment it for now (u can do a condition) ");
		
		//Run the initial creating just if the data empty 
		if (singlePropNameRepository.count()<1){
			System.out.println("Creating initial data");
			entitiesService.createInitialData();
		}
		
		
		
		//Get the data as optional
		//Optional <SinglePropName> dbData1 =  singlePropNameRepository.findById(4L);
		Iterator <SinglePropName> a = singlePropNameRepository.findAll().iterator();
		List<SinglePropName> singleValuesList2 = new ArrayList<>();
		//asssign it to property . 
		//SinglePropName singlePropName3 = dbData1.get();
		//System.out.println(singlePropName3.getPropName());
		System.out.println(a);
		Map<String,SinglePropName > singlePropsMapList = new TreeMap<String,SinglePropName>();
		for (; a.hasNext();){
			SinglePropName s = (SinglePropName) a.next(); 
            System.out.print(s +"\n"); 
            singleValuesList2.add(s);
            singlePropsMapList.put(s.getPropName(), s);
            //System.out.print("  Last Name: " + employee.getLastName()); 
            //System.out.println("  Salary: " + employee.getSalary()); 
         }
		SinglePropName singlePropName11 = singleValuesList2.get(0);
		System.out.println("singlePropName11 = \n" + singlePropName11 + "\n ???");
		SinglePropName singlePropName12 = singlePropsMapList.get("name");
		System.out.println("singlePropName12 = \n" + singlePropName12 + "\n ???");
		System.out.println(singlePropName12.getId());
		
		System.out.println("Now from the service : ");
		
		//Map of the values : 
		Map<String, String> singlePropsValues = new TreeMap<String, String>();
		singlePropsValues.put("name", "Shirley Games");
		singlePropsValues.put("address", "Kefar Saba");

		 
		List<SinglePropValue> singleValuesList = entitiesService.generateSingleValuesList(singlePropsValues);
		
		
		//creating new socEvent
		//SocialEvent(String description, String moreValue, int fromAge, int toAge, List<SinglePropValue> singleValuesList)
		SocialEvent socialEvent1 = new SocialEvent("desc","bla", 3, 4,  singleValuesList);
		
		//save it 
		socialEventRepository.save(socialEvent1);
		//Check the db for seeing that.
		
		System.out.println("Use in the social evnent generator  : ");
		singlePropsValues.put("name", "Trip in Pesach");
		singlePropsValues.put("address", "Western wall");
		
		entitiesService.createSocialEvent(singlePropsValues);
		
		System.out.println("Trying create Multi prop");
		
		MultiPropName multiPropName = new MultiPropName("Event-Type");
		List <String> multiPropValues = new ArrayList<String>();
		multiPropValues.add("Trip");
		multiPropValues.add("Party");
		multiPropValues.add("Lesson");
		multiPropNameRepository.save(multiPropName);
		MultiPropValue multiPropValue = new MultiPropValue(multiPropName, multiPropValues);
		multiPropValueRepository.save(multiPropValue);
		
		
		//adding one more
		

		MultiPropName multiPropName2 = new MultiPropName("jewLvl");
		List <String> multiPropValues2 = new ArrayList<String>();
		multiPropValues2.add("Shabbat");
		multiPropValues2.add("Later Shabbat");
		multiPropValues2.add("type3");
		multiPropNameRepository.save(multiPropName2);
		MultiPropValue multiPropValue2 = new MultiPropValue(multiPropName2, multiPropValues2);
		multiPropValueRepository.save(multiPropValue2);
		List<MultiPropValue> someMultiProps = new ArrayList<>();
		someMultiProps.add(multiPropValue);
		someMultiProps.add(multiPropValue2);
		List<SinglePropValue> singleValuesList3 = entitiesService.generateSingleValuesList(singlePropsValues);
		SocialEvent socialEvent2 = new SocialEvent("desc","bla", 3, 4,  singleValuesList3,someMultiProps);
		socialEventRepository.save(socialEvent2);
		
		
		MultiPropName multiPropName3 = new MultiPropName("jewLvl");
		List <String> multiPropValues3 = new ArrayList<String>();
		multiPropValues3.add("Shabbat");
		multiPropValues3.add("Later Shabbat");
		multiPropValues3.add("type3");
		multiPropNameRepository.save(multiPropName3);
		MultiPropValue multiPropValue3 = new MultiPropValue(multiPropName3, multiPropValues3);
		multiPropValueRepository.save(multiPropValue3);
		List<MultiPropValue> someMultiProps2 = new ArrayList<>();
		
		someMultiProps2.add(multiPropValue);
		someMultiProps2.add(multiPropValue3);
		List<SinglePropValue> singleValuesList4 = entitiesService.generateSingleValuesList(singlePropsValues);
		SocialEvent socialEvent3 = new SocialEvent("desc22","bla", 3, 4,  singleValuesList4,someMultiProps2);
		//socialEventRepository.save(socialEvent3);

		
		


		
	}
}