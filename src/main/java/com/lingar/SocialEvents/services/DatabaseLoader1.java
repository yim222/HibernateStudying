package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lingar.SocialEvents.entities.MultiPropLvl1Value;
import com.lingar.SocialEvents.entities.MultiPropLvl2Value;
import com.lingar.SocialEvents.entities.MultiPropName;
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
	private final MultiPropLvl2ValueRepository multiPropLvl2ValueRepository;
	private final LingarPropNameRepository lingarPropNameRepository;
	private final LingarPropValueRepository lingarPropValueRepository;

	
	//First get the repository2 of the CRUD
	@Autowired
	public DatabaseLoader1(SocialEventRepository repository22,
			SinglePropNameRepository singlePropNameRepository,
			SinglePropValueRepository singlePropValueRepository,
			MultiPropNameRepository multiPropNameRepository,
			MultiPropValueRepository multiPropValueRepository,
			MultiPropLvl2ValueRepository multiPropLvl2ValueRepository,
			LingarPropNameRepository lingarPropNameRepository,
			LingarPropValueRepository lingarPropValueRepository
			
			) {
		//We create Repositoris to each entity - if U want a full control on the using. 
		this.socialEventRepository = repository22;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		this.multiPropNameRepository = multiPropNameRepository;
		this.multiPropValueRepository = multiPropValueRepository;
		this.multiPropLvl2ValueRepository = multiPropLvl2ValueRepository;
		this.lingarPropNameRepository = lingarPropNameRepository;
		this.lingarPropValueRepository = lingarPropValueRepository;
		
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
				singlePropValueRepository,
				lingarPropNameRepository,
				lingarPropValueRepository);
		
		
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
		//socialEventRepository.save(socialEvent1);
		//Check the db for seeing that.
		
		System.out.println("Use in the social evnent generator  : ");
		singlePropsValues.put("name", "Trip in Pesach");
		singlePropsValues.put("address", "Western wall");
		
		entitiesService.createSocialEvent(singlePropsValues);
		
		System.out.println("Trying create Multi prop");
		
		
		//lvl2Values.add(new MultiPropLvl2Value("kkk"));
		//lvl2Values.add(new MultiPropLvl2Value("jjj"));
		MultiPropName multiPropName = new MultiPropName("EventType");
		MultiPropName multiPropName2 = new MultiPropName("area");
		multiPropNameRepository.save(multiPropName);
		multiPropNameRepository.save(multiPropName2);

		List <String> multiPropValues = new ArrayList<String>();
		multiPropValues.add("Trip");
		multiPropValues.add("Party");
		multiPropValues.add("Lesson");
		multiPropNameRepository.save(multiPropName);
		
		///Create new list for the lvl2 values
		List<MultiPropLvl2Value> lvl2Values = new ArrayList<>();
		List<MultiPropLvl2Value> lvl2Values2 = new ArrayList<>();
		List<MultiPropLvl2Value> lvl2Values3 = new ArrayList<>();


		MultiPropLvl2Value ma = new MultiPropLvl2Value("kkk");
		MultiPropLvl2Value mb = new MultiPropLvl2Value("jjj"); 
		multiPropLvl2ValueRepository.save(ma);
		multiPropLvl2ValueRepository.save(mb);
		MultiPropLvl2Value mc = new MultiPropLvl2Value("ccc");
		MultiPropLvl2Value md = new MultiPropLvl2Value("ddd"); 
		//multiPropLvl2ValueRepository.save(ma);
		//multiPropLvl2ValueRepository.save(mb);
		multiPropLvl2ValueRepository.save(mc);
		multiPropLvl2ValueRepository.save(md);
		List<MultiPropLvl1Value> multiPropsValuesList = new ArrayList<>();
		

		
		lvl2Values.add(ma);
		lvl2Values.add(mb);
		
		lvl2Values2.add(mc);
		lvl2Values2.add(md);
		
		MultiPropLvl1Value multiPropValue = new MultiPropLvl1Value(multiPropName, multiPropValues,lvl2Values);
		MultiPropLvl1Value multiPropValue2 = new MultiPropLvl1Value(multiPropName2, multiPropValues,lvl2Values2);
		multiPropValueRepository.save(multiPropValue);
		multiPropValueRepository.save(multiPropValue2);

		
		
		multiPropsValuesList.add(multiPropValue);
		multiPropsValuesList.add(multiPropValue2);
		
		

		//multiPropValueRepository.save(multiPropValue);
		SocialEvent socialEvent = new SocialEvent("ttt", "sss", 5, 6, singleValuesList, multiPropsValuesList);
		socialEventRepository.save(socialEvent);
		
		
		//trying to mix different the lv2 value for more event
		
		lvl2Values3.add(ma);
		lvl2Values3.add(md);
		
		//Need to do a new multpiproplvl1? 
		List<MultiPropLvl1Value> multiPropsValuesList2 = new ArrayList<>();
		MultiPropLvl1Value multiPropValue3 = new MultiPropLvl1Value(multiPropName, multiPropValues,lvl2Values3);
		multiPropValueRepository.save(multiPropValue3);//->he problem
		multiPropsValuesList2.add(multiPropValue3);

		SocialEvent socialEvent3 = new SocialEvent();
		//socialEvent3.setSinglePropsValuesList(singleValuesList);--> This parameter cause the duplicate problem
		socialEvent3.setDescription("new event");
		socialEvent3.setMultiPropsValuesList(multiPropsValuesList2);
		socialEventRepository.save(socialEvent3);
		
		

		
		/*
		MultiPropName multiPropName = new MultiPropName("Event-Type");
		List <String> multiPropValues = new ArrayList<String>();
		multiPropValues.add("Trip");
		multiPropValues.add("Party");
		multiPropValues.add("Lesson");
		multiPropNameRepository.save(multiPropName);
		MultiPropLvl1Value multiPropValue = new MultiPropLvl1Value(multiPropName, multiPropValues);
		multiPropValueRepository.save(multiPropValue);
		
		
		//adding one more
		

		MultiPropName multiPropName2 = new MultiPropName("jewLvl");
		List <String> multiPropValues2 = new ArrayList<String>();
		multiPropValues2.add("Shabbat");
		multiPropValues2.add("Later Shabbat");
		multiPropValues2.add("type3");
		multiPropNameRepository.save(multiPropName2);
		MultiPropLvl1Value multiPropValue2 = new MultiPropLvl1Value(multiPropName2, multiPropValues2);
		multiPropValueRepository.save(multiPropValue2);
		List<MultiPropLvl1Value> someMultiProps = new ArrayList<>();
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
		MultiPropLvl1Value multiPropValue3 = new MultiPropLvl1Value(multiPropName3, multiPropValues3);
		multiPropValueRepository.save(multiPropValue3);
		List<MultiPropLvl1Value> someMultiProps2 = new ArrayList<>();
		
		someMultiProps2.add(multiPropValue);
		someMultiProps2.add(multiPropValue3);
		List<SinglePropValue> singleValuesList4 = entitiesService.generateSingleValuesList(singlePropsValues);
		SocialEvent socialEvent3 = new SocialEvent("desc22","bla", 3, 4,  singleValuesList4,someMultiProps2);
		//socialEventRepository.save(socialEvent3);
		
		*/
		//lvl2Values.add(new MultiPropLvl2Value("kkk"));
		//lvl2Values.add(new MultiPropLvl2Value("jjj"));

		entitiesService.trying1();
		


		
	}
}