package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingar.SocialEvents.entities.MultiPropName;
import com.lingar.SocialEvents.entities.MultiPropValue;
import com.lingar.SocialEvents.entities.SinglePropName;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;
import com.lingar.SocialEvents.helper.EntitiesServiceHelper;
import com.lingar.SocialEvents.systemConstants.DataConstants;

@Service
public class EntitiesService {
	public final SocialEventRepository socialEventRepository;
	private final SinglePropNameRepository singlePropNameRepository;
	private final SinglePropValueRepository singlePropValueRepository;
	private final MultiPropNameRepository multiPropNameRepository;
	private final MultiPropValueRepository multiPropValueRepository;

	
	//List of the constant properties
	public static final String[] SINGLE_PROPS_VALUES = DataConstants.SINGLE_PROPS_VALUES;
	public static final Map <String, List<String>> MULTI_PROPS_VALUES = DataConstants.MULTI_PROPS_VALUES ;
	
	public void test1(){
		System.out.println("I am test of entity service. ");
	}

	
	@Autowired //The default of what's happen when this is created. 
	public EntitiesService(SocialEventRepository socialEventRepository,
			SinglePropNameRepository singlePropNameRepository,
			SinglePropValueRepository singlePropValueRepository,
			MultiPropNameRepository multiPropNameRepository,
			MultiPropValueRepository multiPropValueRepository
			){
		
		System.out.println("hello I am EntitiesService");
		this.socialEventRepository = socialEventRepository;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		this.multiPropNameRepository = multiPropNameRepository;
		this.multiPropValueRepository = multiPropValueRepository;
		
		
	}
	/**PREPARED*/
	//It's generate singleValuesList with the already defined - 
	public List<SinglePropValue> generateSingleValuesList(Map<String, String> values){
		
		//Getting all singlePropsNames from the DB 
		Iterator <SinglePropName> singlePropsNames = singlePropNameRepository.findAll().iterator();
		
		//A list for work on and assign the right values
		List<SinglePropValue> singleValuesList = new ArrayList<>();
		
		System.out.println(singlePropsNames);
		
		//Tree Map for assign the values with their correspond key (maybe it's not necessary check in the future)
		Map<String,SinglePropName > singlePropsMapList = new TreeMap<String,SinglePropName>();
		
		for (; singlePropsNames.hasNext();){
			//get the current loop element 
			SinglePropName singlePropName = (SinglePropName) singlePropsNames.next(); 
			
            System.out.print(singlePropName +"\n"); 
            
            
            
            //Assign it to the TreeMap
            singlePropsMapList.put(singlePropName.getPropName(), singlePropName);
            
         }
		
		System.out.println("singlePropsMapList: \n" +  singlePropsMapList);
		
		
		//Now loop over the Map of singles Names entities and create single Values by the Map passed of values
		for (Map.Entry<String, String> entry : values.entrySet()) {
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    
		    //Get the right SinglePropName by the key
		    SinglePropName singlePropName = singlePropsMapList.get(entry.getKey());
		    
		    //Get the current value
		    String propValue = entry.getValue();
		    
		    //Assign them both (creating the new value //in the multi u need to check if that exist also. 
		    SinglePropValue singlePropValue = new SinglePropValue(singlePropName, propValue);
		  //save it to database
            singlePropValueRepository.save(singlePropValue);
		    //Adding them to the list 
		    singleValuesList.add(singlePropValue);
		    
		    
		}
		
		System.out.println("singleValuesList:\n" + singleValuesList);
		
		return singleValuesList;
		
	}
	
	
	//Methood for generate from Map<String, List<String> > values (propName, values...)
	//for pass it to social event. 
	public Set<MultiPropValue> generateMultiValuesListOLD(Map<String, List<String>> values){
		
		System.out.println("check generateMultiValuesList");
		
		//Data for testing - suppose to came from parameters
		Map<String, List<String>> values2 = new HashMap<>();
		List<String> nestedValues2 = new ArrayList<>();
		nestedValues2.add("multi trip");
		nestedValues2.add("simple trip");
		nestedValues2.add("lecture");
				
		//AreaMulti
		values2.put("EventTypeMulti", nestedValues2);
		
		//-End of testing
		
		
		//Preparation works:
		//create Set<MultiPropValue>  multiPropValues that will contain the Values
		Set<MultiPropValue>  multiPropValues = new HashSet<>();
		
		//Get all the MultiPropNames from the DB 
		Iterator <MultiPropName> allMultiPropsNames = multiPropNameRepository.findAll().iterator();
		
		//Get all the MutliPropValues from the DB 
		Iterator <MultiPropValue> allMultiPropsValues = multiPropValueRepository.findAll().iterator();
		
		//Create locals for using in the loop : 
		String multiPropName ="";
		String multiPropValue = "";
		MultiPropName multiPropNameObj = null;
		MultiPropValue multiPropValueObj = null;
		Set<MultiPropValue>  multiPropValues1 = new HashSet<>();
		
		
		System.out.println(multiPropNameRepository.findByMultiName("AreaMulti"));
		System.out.println(multiPropNameRepository.existsByMultiName("AreaMulti2"));
		
		//Loop on the values:
		
		//Each entrySet (=object in the map) 
		for (Map.Entry<String,  List<String>> element : values2.entrySet()){
		
			//get the key == the name of the multi prop
			multiPropName = element.getKey();
			//check if the key is exist as MutliPropName (basically we don't check and use an exist prop, it's for other using in the continue)
			//If does - get it
			if(multiPropNameRepository.existsByMultiName(multiPropName)){
				
				System.out.println("Getting the multiValueProp :  ");
				multiPropNameObj = multiPropNameRepository.findByMultiName(multiPropName).get(0);
				System.out.println(multiPropNameObj);

			}
			
			//If not - create it and save it 
			else{
				System.out.println("Creating the multiValueProp :  ");
				multiPropNameObj = new MultiPropName(multiPropName);
				multiPropNameRepository.save(multiPropNameObj);
				System.out.println(multiPropNameObj);

			}
			//multi_prop_name_id
		
			//Get all values that connected to the current prop (by id?) 
			System.out.println("getting the prop name connected values");
			System.out.println(multiPropValueRepository.findByMultiPropName(multiPropNameObj));
			
			System.out.println("getting the prop name connected values by Id");
			System.out.println(multiPropValueRepository.findByMultiPropNameId(multiPropNameObj.getId()));
			
			//get the values  currently assign to the this propName
			multiPropValues1 = multiPropValueRepository.findByMultiPropNameId(multiPropNameObj.getId());
			
			//
			//Loop on the nested values :
			for(String propValue : element.getValue() ){
				//check if the current value is exist .  - U here - it's should to be true but shows false. 
				System.out.println("Test - checking if the propValues are exist. \n checking  " + propValue + " propName id = " + multiPropNameObj.getId());
				//System.out.println(multiPropValueRepository.existsByMultiPropNameId(1L));
				System.out.println(multiPropValueRepository.existsByPropValue("multi trip"));
				System.out.println(multiPropValueRepository.existsByPropValueAndMultiPropNameId(propValue, multiPropNameObj.getId()));
				
				//System.out.println(multiPropValueRepository.exists(propValue, multiPropNameObj.getId()));
				//System.out.println(multiPropValueRepository.existsIfBlaBla(propValue, multiPropNameObj.getId()));
				
				if(multiPropValueRepository.existsByPropValueAndMultiPropNameId(propValue, multiPropNameObj.getId())){
					
					System.out.println(propValue + " is exist ... getting it ");
					multiPropValueObj = multiPropValueRepository.findByPropValue(propValue);
					System.out.println(multiPropValueObj);
					
					
					//multiPropNameObj = multiPropNameRepository.findByMultiName(multiPropName).get(0);
					System.out.println(multiPropNameObj);

				}
				
				//If not - create it and save it 
				else{
					System.out.println(propValue + " isn't exist ");
					/*
					System.out.println("Creating the multiValueProp :  ");
					multiPropNameObj = new MultiPropName(multiPropName);
					multiPropNameRepository.save(multiPropNameObj);
					System.out.println(multiPropNameObj);
					*/

				}
			}
			
			
			
			
			//check if the value is exist as MutliPropValue
			
			//If does - get it. If not - create it. 
		
			//Assign it to the local multiPropValues
			
		//continue to loop on all
			
		}//End of the level 1 loop 
		//At the end return the multiPropValues
		
		return null;
	}
	
	/**PREPARED*/
	//Method for creating the initial needed data 
	//Maybe it's should be "static" but I don't want to make right now unneeded mix
	public void createInitialData(){
		if (singlePropNameRepository.count()>0){
			System.out.println("Data already exists - not need to create initial Data");
		}
		
		System.out.println("Strating create initial data ... ");
		createInitialSinglesProps();
		createInitialmultieProps();
		
		System.out.println("Finishing create initial data ... ");

	}
	
	public void createInitialSinglesProps(){
		System.out.println("Creating initials Singles Props");
		SinglePropName singlePropName = new SinglePropName();
		for(String propName : SINGLE_PROPS_VALUES ){
			
			singlePropName = new SinglePropName(propName);
			singlePropNameRepository.save(singlePropName);
			
		}
		
		
		System.out.println("All Entities saved. ");
		
	}
	
	public void createInitialmultieProps(){
		System.out.println("Creating initials Multi Props");

		System.out.println("The default values : ");
		System.out.println(MULTI_PROPS_VALUES);
		
		for (Map.Entry<String, List<String>> e : MULTI_PROPS_VALUES.entrySet()){
			String propName = e.getKey();
			System.out.println("Saving multiPropName " + propName);
			MultiPropName mProp = new MultiPropName(propName);
			multiPropNameRepository.save(mProp);
			
			for(String propValue : e.getValue()){
				System.out.println("Saving multiPropValue " + propValue+ " of " +  propName);
				MultiPropValue mPropVal = new MultiPropValue(mProp, propValue);
				multiPropValueRepository.save(mPropVal);

			}
			
			
		}
		System.out.println("All MultiProps have saved");
	}
	
	/**PREPARED*/
	public void createSocialEvent(Map<String, String> singleValues,
			Map<String, List<String>> multiValues,
			String comment){
		
		List<SinglePropValue> singlePropValues = generateSingleValuesList(singleValues);
		Set<MultiPropValue> multiPropValues = generateMultiValuesList( multiValues);

		SocialEvent socialEvent = new SocialEvent();
		socialEvent.setSinglePropsValuesList(singlePropValues);
		socialEvent.setMultiPropsValuesSet(multiPropValues);
		socialEvent.setLingarComment(comment);
		socialEventRepository.save(socialEvent);
		
	}
	
	/**PREPARED - with all properties */
	public void createSocialEvent(Map<String, String> singleValues,
			Map<String, List<String>> multiValues,
			String comment, 
			Date date, int[] time,
			int[] agesRange){
		
		List<SinglePropValue> singlePropValues = generateSingleValuesList(singleValues);
		Set<MultiPropValue> multiPropValues = generateMultiValuesList( multiValues);

		SocialEvent socialEvent = new SocialEvent();
		socialEvent.setSinglePropsValuesList(singlePropValues);
		socialEvent.setMultiPropsValuesSet(multiPropValues);
		socialEvent.setLingarComment(comment);
		socialEvent.setDate(date);
		socialEvent.setInts(agesRange, time);
		
		socialEventRepository.save(socialEvent);
		
	}
	
	/**PREPARED*/
	public Set<MultiPropValue> generateMultiValuesList(Map<String, List<String>> values){
		
		System.out.println("*****************************START - TRYING new generate Multi values list******************************** ");
		/**Method explain : */
		//U get Map with many entries that each have “propName” and “Values” – as strings 
		//The method generates Set of MultiPropValues.
		
		//Mock Data for testing - suppose to came from parameters
		Map<String, List<String>> values2 = new HashMap<>();
		List<String> nestedValues2 = new ArrayList<>();
		
		//adding some props of "EventType"
		nestedValues2.add("multi trip");
		nestedValues2.add("simple trip");
		nestedValues2.add("lecture");
				
		values2.put("EventTypeMulti", nestedValues2);
		
		//adding some props of "matchingIdea"
		nestedValues2 = new ArrayList<>();
		
		nestedValues2.add("spontanic");
		nestedValues2.add("Connector");
		nestedValues2.add("Saving details");
		
		values2.put("matchingIdea", nestedValues2);
		//-End of testing MOCK
		
		//Preparation tasks- 
		//Create locals for using in the loop : 
		String multiPropName ="";
		String multiPropValue = "";
		MultiPropName multiPropNameObj = null;
		MultiPropValue multiPropValueObj = null;
		Set<MultiPropValue>  multiPropValues = new HashSet<>();//TreeSet<>();

		//Start work ... 
		//Loop on all entries - each entry is propName that contain one or more connected values 
		for (Map.Entry<String,  List<String>> elementOfValues : values.entrySet()){
			String propNameStr = elementOfValues.getKey();
			System.out.println("Working on the prop name : " + propNameStr);
			
			//Check if the propName exist – if does – get it, if not – create it. 
			boolean propNameExists = multiPropNameRepository.existsByMultiName(propNameStr);
			System.out.println("Is " + propNameStr + " already exists ? " + propNameExists );
			
			if(propNameExists){
				System.out.println(propNameStr + " exists. Getting it - ");
				multiPropNameObj = multiPropNameRepository.findByMultiName(propNameStr).get(0);//Should be return object - int the future
				System.out.println(multiPropNameObj);
			}
			else{
				System.out.println(propNameStr + " doesn't exist. Creating and saving it - ");
				multiPropNameObj = new MultiPropName(propNameStr);
				multiPropNameRepository.save(multiPropNameObj);
				System.out.println(multiPropNameObj);
			}
			
			//Loop on the inner values of the propName. 
			System.out.println("Looping on the inner values of the " + propNameStr );
			List<String> currentLoopValues = elementOfValues.getValue();
			//get the id of the propNameObj for using it . 
			long multiPropNameId = multiPropNameObj.getId();
			for(String propValue : currentLoopValues){
				
				//On each – check if exist with the value and the propNameId. 
				boolean propValueExist = multiPropValueRepository.existsByPropValueAndMultiPropNameId(propValue, multiPropNameId);
				
				//If does – get it with the propNameId 
				if(propValueExist){
					System.out.println(propValue + " is already exist. Getting it");
					multiPropValueObj = multiPropValueRepository.findByPropValueAndMultiPropNameId(propValue, multiPropNameId);
					System.out.println(multiPropValueObj);
				}
				
				//If not – create it with the propName. 
				else{
					System.out.println(propValue + " isn't exist. Creating it ... ");
					multiPropValueObj = new MultiPropValue(multiPropNameObj, propValue);
					multiPropValueRepository.save(multiPropValueObj);
					System.out.println(multiPropValueObj);
				}
				//add it to the Set of MultiPropValues. 
				System.out.println("Adding the propValues to the list - ");
				multiPropValues.add(multiPropValueObj);
				System.out.println(multiPropValues);
				
			}
		}
		
		
		System.out.println("*****************************END - TRYING new generate Multi values list******************************** ");

		return multiPropValues;
		
	}
	String[] a = {"eventName",
			"isOrganizer",
			"publisherName",
			"address",
			"shortDescription",
			"longDescription",
			"eventLink",
			"imgUrl",
			"price"};
	public void create10EventsMock(){
		System.out.println("Creating 10 event Mocks");
		EntitiesServiceHelper.create10SocialEventMock(this);
		
		
		
	}
	
	public void displayEventsShort(List<SocialEvent> events){
		//EntitiesServiceHelper.displayShortsEvents((List<SocialEvent>)socialEventRepository.findAll());
		//List<SocialEvent> events = (List<SocialEvent>)socialEventRepository.findAll();
		for (SocialEvent event : events){
			System.out.println("ID = " +event.getId() + " , comment --> " + event.getLingarComment());
			System.out.println("Event-name : " );
			//System.out.println(event.getSinglePropsValuesList());
			List<SinglePropValue> singlesValue = event.getSinglePropsValuesList();
			
			for(SinglePropValue s :singlesValue){
				if(s.getSinglePropName().getPropName().equals("eventName")){
					System.out.println(s.getPropValue());
					break;
				}
				
			}
			
			System.out.println("From age " +  event.getFromAge() + " to age : " + event.getToAge() 
			 + "\nIn date : " + event.getDate() + "\nComment: " +  event.getLingarComment());
			Set<MultiPropValue> multies = event.getMultiPropsValuesSet();
			for(MultiPropValue multiProp : multies){
				//area , jewLvlKeep, eventType
				String name = multiProp.getMultiPropName().getMultiName();
				if (name .equals("eventType")
						|| name .equals("jewLvlKeep") 
						|| name .equals("area")){
					System.out.println(name + ": " + multiProp.getPropValue() );
					
				}
				//System.out.println();
			}
			
			
			System.out.println("*********************************");
		}
		
	}
	
	
	
	public void trying1(){
		
		//A list for work on and assign the right values
		List<MultiPropValue> multiValuesList = new ArrayList<>();
		List<MultiPropValue> multiValuesList2 = new ArrayList<>();
		
		Set<MultiPropValue> multiValuesSet = new HashSet<MultiPropValue>();
		Set<MultiPropValue> multiValuesSet2 = new HashSet<MultiPropValue>();


		MultiPropName multiPropName1 = new MultiPropName("EventTypeMulti");
		multiPropNameRepository.save(multiPropName1);
		MultiPropValue multiPropValue1 = new MultiPropValue(multiPropName1, "multi party");
		MultiPropValue multiPropValue2 = new MultiPropValue(multiPropName1, "multi trip");
		multiValuesList.add(multiPropValue1);
		multiValuesList.add(multiPropValue2);
		
		multiValuesSet.add(multiPropValue1);
		multiValuesSet.add(multiPropValue2);


		multiPropValueRepository.saveAll(multiValuesList);
		///saving to event
		SocialEvent s1 = new SocialEvent();
		s1.setLingarComment("multi event 1");
		//s1.setMultiValuesList(multiValuesList);multiValuesSet
		s1.setMultiPropsValuesSet(multiValuesSet);
		socialEventRepository.save(s1);
		
		
		//saving another event		
		SocialEvent s2 = new SocialEvent();
		s2.setLingarComment("multi event 2");
		s2.setMultiPropsValuesSet(multiValuesSet);
		//s2.setMultiValuesList(multiValuesList);
		//socialEventRepository.save(s2); - here it's make problem 
		s2.setMultiPropsValuesSet(multiValuesSet);
		socialEventRepository.save(s2);
		
		
		MultiPropName multiPropName2 = new MultiPropName("AreaMulti");
		multiPropNameRepository.save(multiPropName2);
		MultiPropValue multiPropValue3 = new MultiPropValue(multiPropName2, "multi north");
		MultiPropValue multiPropValue4 = new MultiPropValue(multiPropName2, "multi south");
		MultiPropValue multiPropValue5 = new MultiPropValue(multiPropName2, "check");

		multiPropValueRepository.save(multiPropValue3);
		multiPropValueRepository.save(multiPropValue4);
		multiPropValueRepository.save(multiPropValue5);
		
		multiValuesSet2.add(multiPropValue3);
		multiValuesSet2.add(multiPropValue4);
		
		SocialEvent s3 = new SocialEvent();
		s3.setLingarComment("multi event 3");
		s3.setMultiPropsValuesSet(multiValuesSet2);
		socialEventRepository.save(s3);
		
		//TRYING with Set of SEt
		multiValuesSet2.addAll(multiValuesSet);
		System.out.println(multiValuesSet2);
		SocialEvent s4 = new SocialEvent();
		s4.setLingarComment("multi event 4");
		s4.setMultiPropsValuesSet(multiValuesSet2);
		socialEventRepository.save(s4);
		
		System.out.println("TRYING MultiPropValuesGenerator : ");
		
		//Mock Data for testing - suppose to came from parameters
		Map<String, List<String>> values2 = new HashMap<>();
		List<String> nestedValues2 = new ArrayList<>();
		
		//adding some props of "EventType"
		nestedValues2.add("multi trip");
		nestedValues2.add("simple trip");
		nestedValues2.add("lecture");
				
		values2.put("EventTypeMulti", nestedValues2);
		
		//adding some props of "matchingIdea"
		nestedValues2 = new ArrayList<>();
		
		nestedValues2.add("spontanic");
		nestedValues2.add("Connector");
		nestedValues2.add("Saving details");
		nestedValues2.add("DANCING");

		
		values2.put("matchingIdea", nestedValues2);
		//-End of testing MOCK
		generateMultiValuesList(values2);

	}
	
	public void trying2(){
		System.out.println("Test the social-event Generator. \n create two events with that ... ");
		Map<String, String> singleValues = new TreeMap<>();
		Map<String, List<String>> multiValues = new TreeMap<>();
		//List<String> innerValues = new ArrayList<>();
		
		singleValues.put("name", "great party2");
		singleValues.put("address", "Tel Aviv");
		System.out.println(singleValues);
		
		List<String> innerValues = new ArrayList<>();
		
		//adding some props of "EventType"
		innerValues.add("multi trip");
		innerValues.add("simple trip");
		innerValues.add("lecture");
				
		multiValues.put("Multi", innerValues);
		
		//adding some props of "matchingIdea"
		innerValues = new ArrayList<>();
		
		innerValues.add("spontanic");
		innerValues.add("Connector");
		innerValues.add("Saving details");
		innerValues.add("DANCING");

		
		multiValues.put("matchingIdea", innerValues);
		
		createSocialEvent(singleValues, multiValues, "event1");
		
		//creating one more
		
		singleValues.put("name", "Holiday trip 2");
		singleValues.put("address", "jerusalem");
		System.out.println(singleValues);
		
		innerValues.remove(2);
		System.out.println(multiValues);
		innerValues = new ArrayList<>();
		
		innerValues.add("gaming");
		innerValues.add("Meeting");
		multiValues.put("eventTypeMulti", innerValues);
		createSocialEvent(singleValues, multiValues, "event2");
		

		singleValues.put("name", "Resterunt");
		singleValues.put("address", "Holonn");
		innerValues.remove(1);
		innerValues.add("vaacation");
		innerValues = new ArrayList<>();
		
		innerValues.add("Connector");
		innerValues.add("Spontanic");
		multiValues.put("matchingIdea", innerValues);
		createSocialEvent(singleValues, multiValues, "event3");
		
		//System.out.println(socialEventRepository.findAll());
		
		
		
		

	}
	
	public void trying3(){
		
		System.out.println("\n*****************************TRYING - 3 *******************************************\n");
		
		System.out.println("U can see here I get all elements (socialEvents) That have in their array (multiProps) \n "
				+ "The provided value of possible element. ");
		System.out.println("TRYING to find element from the multiPropValues : ");
		String name1, value1;
		Set<MultiPropValue> m1 = new HashSet<>();
		name1 = "EventTypeMulti"; value1 = "multi party";
		System.out.println("TRYING to get the prop Value : " + name1 + " - " + value1);
		MultiPropValue m = multiPropValueRepository.findByPropValueAndMultiPropNameMultiName(value1, name1);
		m1.add(m);
		System.out.println(m);
		List<SocialEvent> s1 = socialEventRepository.findByMultiPropsValuesSetIn(m);
		System.out.println(s1);
		
		
		
		System.out.println("TRYING to find element from the multiPropValues : ");
		
		name1 = "matchingIdea"; value1 = "spontanic";
		System.out.println("TRYING to get the prop Value : " + name1 + " - " + value1);
		m = multiPropValueRepository.findByPropValueAndMultiPropNameMultiName(value1, name1);
		System.out.println(m);
		List<SocialEvent> s2 = socialEventRepository.findByMultiPropsValuesSetIn(m);
		System.out.println(s2);
		
		System.out.println("What's happen if this property not exist ? ");
		name1 = "not exist"; value1 = "spontanic";
		System.out.println("TRYING to get the prop Value : " + name1 + " - " + value1);
		m = multiPropValueRepository.findByPropValueAndMultiPropNameMultiName(value1, name1);
		System.out.println(m);
		//U here - greate - it's work - tomorrow try to do that with list of array . If it's work  - u close the issue of the multi. 
		//U remain with the range and date. I assume it's work but the truth it will be very long. 
		//Maybe try to do named Query. U need to practice it too. 
		m1 = new HashSet<>();
		m1.add(m);
		Set <MultiPropValue >m3 = new HashSet<>();
		System.out.println("adding some properties");
		m3.add(multiPropValueRepository.findByPropValueAndMultiPropNameMultiName("multi trip", "EventTypeMulti"));
		m3.add(multiPropValueRepository.findByPropValueAndMultiPropNameMultiName("connector", "matchingIdea"));
		//m3.add(multiPropValueRepository.findByPropValueAndMultiPropNameMultiName("multi trip", "EventTypeMulti"));
		System.out.println(m3);

		MultiPropValue m2 = new MultiPropValue();
		//System.out.println("TRYING -2  with set of values \n" + socialEventRepository.findByMultiPropsValuesSetIn(m3));
		System.out.println("Should return 23,28,32 only ");
		System.out.println("But it's work with or so it's return each element that contain one of them. ");
	}

	public void draftTrying (){
		System.out.println(   singlePropValueRepository.findBySinglePropNamePropName("eventName")    );
		//System.out.println(   singlePropValueRepository.findBySinglePropNamePropNameAndSocialEvnetId("eventName", 41L)    );
		System.out.println("the result of the query:");
		System.out.println(socialEventRepository.tryQuery2("eventName", 41));
		System.out.println("the result of the query 3:");
		//System.out.println(socialEventRepository.tryQuery3("eventName", 41));

	}
	
	public void tryingFilter(){
		Map<String, List<String>> passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);
		//Copying MultiValues - not what u need. U need to generate List of MultiProps
		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		
		Map<String, List<String>> passedMultiValues2 = new TreeMap<>();
		List<String> myList = passedMultiValues.get("eventType");
		myList.remove(3);		myList.remove(2);		myList.remove(0);
		passedMultiValues2.put("eventType", myList);
		
		/*
		Set<MultiPropValue> list1 = new HashSet<>();
		MultiPropName propName = new MultiPropName("eventType");
		list1.add(new MultiPropValue(propName, "meeting"));
		System.out.println("The filter - only meetings :");
		
		*/
		
		//generate from the values - list of MultiPropValues
		HashSet<MultiPropValue> list2 = (HashSet<MultiPropValue>)generateMultiValuesList(passedMultiValues2);
		List<MultiPropValue> list3 = new ArrayList<>(list2);
		System.out.println("list2 = " + list2);
		
		//System.out.println(socialEventRepository.filterOne(list2));
		//System.out.println(socialEventRepository.filterTwo(list3.get(0)));
		List <SocialEvent> eventsList = socialEventRepository.findByMultiPropsValuesSetIn(new MultiPropValue());
		System.out.println("Call to the derived method  : \n" 
		); 
		
		displayEventsShort(eventsList);
		
		//TRYING filter 3  
		eventsList = socialEventRepository.filter3(list3.get(0));
		System.out.println("filter 3 - \n" );
		displayEventsShort(eventsList);
		
		list3 = new ArrayList<>(list2);
		passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);
		//Copying MultiValues - not what u need. U need to generate List of MultiProps
		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		System.out.println(passedMultiValues.get("eventType"));
		passedMultiValues.get("eventType").remove(3);passedMultiValues.get("eventType").remove(1);
		
		//eventsList = socialEventRepository.filter4(list3.get(0));
		list2 = (HashSet<MultiPropValue>)generateMultiValuesList(passedMultiValues);
		list3 = new ArrayList<>(list2);

		System.out.println("filter 4 (0nly games ,or  speedate - \n" );
		//System.out.println(" list 3 " + list3.get(index));
		eventsList = socialEventRepository.filter4(list3);
		displayEventsShort(eventsList);
		
	}

	public void tryingFilter2(){
		System.out.println("*******************TRYING filter 2 *****************************");
		List<SocialEvent> allEvents = (List<SocialEvent>)socialEventRepository.findAll();
		//get the initial Multi props as 3 maps
		//copy the lists and pass them as Map
		
		//on event type : 
		List<String> work = MULTI_PROPS_VALUES.get("eventType");
		Map<String, List<String>> mapWork = new TreeMap<>();
		mapWork.put("eventType", work);
		List<MultiPropValue> eventTypeInitial = new ArrayList<>(generateMultiValuesList(mapWork));
		
		//on jewLvlKeep: 
		 work = MULTI_PROPS_VALUES.get("jewLvlKeep");
		mapWork = new TreeMap<>();
		mapWork.put("jewLvlKeep", work);
		List<MultiPropValue> jewLvlKeepInitial =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		//on area: 
		 work = MULTI_PROPS_VALUES.get("area");
		mapWork = new TreeMap<>();
		mapWork.put("area", work);
		List<MultiPropValue> areaInitial =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		System.out.println("The initial Lists : \nEvent type =  " + eventTypeInitial  
				+ "\nJew Level Keep -  " + jewLvlKeepInitial 
				+"\nAreas - " + areaInitial);
		
		//generate 3 list to work with and they can get initials always : 
		List <MultiPropValue> eventTypes, jewLvls, areas;
		List <SocialEvent> resultEvents;
		
		//START TEST 
		//Those steps need to be done in each test . 
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		/*
		 * 
		 * Here u can see how I parse the properties into string. 
		 */
		//List<Integer> idList = students.stream().map(Student::getId).collect(Collectors.toList());
		List<String > properties  = areas.stream().map(MultiPropValue::getPropValue).collect(Collectors.toList());
		//List<String> strProps = properties.stream().map(MultiPropName::getMultiName).collect(Collectors.toList());
		System.out.println(properties);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		
		
		eventTypes.remove(3);eventTypes.remove(0);
		System.out.println("Filter 4 with those types : "  + eventTypes);
		
		//get the events with the fiter :
		resultEvents = socialEventRepository.filter4(eventTypes);
		displayEventsShort(resultEvents);
		
		System.out.println("And without duplicates... : (filter 5 with DISTINCT " );
		
		//get the events with the fiter :
		resultEvents = socialEventRepository.filter5(eventTypes);
		displayEventsShort(resultEvents);
		
		System.out.println("And check if I fix filter two  : " + socialEventRepository.filterTwoFixed(eventTypes.get(0)));
		
		System.out.println("YEAH!!!!!!!!!!!!");
		///END TEST 1
		
		
		//START TEST  2
		//Those steps need to be done in each test . 
		System.out.println("Test 2 now I am tryng to filter with more . TRYING to find only those from the south .  ");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		
		
		eventTypes.remove(3);eventTypes.remove(0);
		areas.remove(3);areas.remove(1);areas.remove(0);
		System.out.println("Filter 6 with those types : "  + eventTypes +  " and those areas : \n" + areas );
		System.out.println("Filter 5 is work -- \n1- with par1 - \n" +socialEventRepository.filter5(eventTypes) 
		+ "\n2- with par2 - \n" +  socialEventRepository.filter5(areas) );
		  //get the events with the fiter :
		resultEvents = socialEventRepository.filter6(eventTypes,areas);

		
		displayEventsShort(resultEvents);
		
		System.out.println("TRYING filter 8");

		eventTypes.remove(0);
		System.out.println("Try again : those are the events with type " +  eventTypes.get(0).getPropValue());
		System.out.println(eventTypes);

		resultEvents = socialEventRepository.filter5(eventTypes);
		
		displayEventsShort(resultEvents);
		//System.out.println(resultEvents);
		//displayEventsShort(socialEventRepository.filter4(areas));
		
		System.out.println("Now I am TRYING to get only from those that in the south : "
				);
		resultEvents = socialEventRepository.filter8(eventTypes, areas);
		displayEventsShort(resultEvents);

		System.out.println();
		
		///END TEST
		

		//START TEST  3
		//Those steps need to be done in each test . 
		System.out.println("Test 3 checking again this :  ");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		System.out.println("all events : \n" );
		displayEventsShort(allEvents);
		eventTypes.remove(2);//eventTypes.remove(0);
		areas.remove(2);areas.remove(1);//areas.remove(0);
		System.out.println("Filter 8 with those types : "  + eventTypes +  "\n and those areas : \n" + areas );
		System.out.println("Those are the events only with the eventType : ");
		resultEvents = socialEventRepository.filter5(eventTypes);
		displayEventsShort(resultEvents);

		//get the events with the fiter :
		System.out.println("************* AND those only from the the areas ");
		resultEvents = socialEventRepository.filter8(eventTypes,areas);
		displayEventsShort(resultEvents);
		
		System.out.println("IS WORK ?  -  YEAH!! " );
		
		//END of TEST 3
		

		
		//START TEST 4
		//Those steps need to be done in each test . 
		System.out.println("Test 4 NOW with JewLvlKeep :  same as before but filter also by jew Level keep");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		System.out.println("all events : \n" );
		displayEventsShort(allEvents);
		eventTypes.remove(2);//eventTypes.remove(0);
		areas.remove(3);areas.remove(0);//areas.remove(0);
		jewLvls.remove(0);
		System.out.println("Filter 9 with those types : "  + eventTypes +  "\n and those areas : \n" + areas  + "and that is the jew keep level\n"
				+ jewLvls);
		System.out.println("Those are the events only with the eventType : ");
		resultEvents = socialEventRepository.filter5(eventTypes);
		displayEventsShort(resultEvents);
		
		//get the events with the fiter :
		System.out.println("************* AND those only from the the areas ");
		//resultEvents = socialEventRepository.filter9(eventTypes,jewLvls,areas);
		resultEvents = socialEventRepository.filter8(eventTypes,areas);
		System.out.println(resultEvents);
		displayEventsShort(resultEvents);
		
		//get the events with the fiter :
		System.out.println("************* AND those only from the the areas and by jew level keep ");
		//resultEvents = socialEventRepository.filter9(eventTypes,jewLvls,areas);
		resultEvents = socialEventRepository.filter10ByMultiProps(eventTypes,jewLvls,areas);
		System.out.println(resultEvents);
		displayEventsShort(resultEvents);
		resultEvents = socialEventRepository.filter9(eventTypes,jewLvls,areas);
		
		//get the events with the fiter :
		System.out.println("************* AND with BETWEEN ");
		//resultEvents = socialEventRepository.filter9(eventTypes,jewLvls,areas);
		resultEvents = socialEventRepository.filter10ByMultiProps(eventTypes,jewLvls,areas);
		System.out.println(resultEvents);
		displayEventsShort(resultEvents);
		resultEvents = socialEventRepository.filter12(eventTypes,jewLvls,areas);
		
		int from = 55; int to = 70;
		
		System.out.println("TRYING to choose the .from age between " + from + " to " + to);
		resultEvents = socialEventRepository.filter13(from, to);
		displayEventsShort(resultEvents);
		
		
		System.out.println("TRYING to choose the with overlap range of  " + from + " to " + to);
		resultEvents = socialEventRepository.filter14(from, to);
		displayEventsShort(resultEvents);
		
		//System.out.println("IS WORK ?  -  YEAH!! " );
		
		//END of TEST 3
		
		System.out.println("TRYING to choose the with overlap range of  " + from + " to " + to);
		resultEvents = socialEventRepository.filter14(from, to);
		displayEventsShort(resultEvents);
		
		//creating dates to test
		Date fromDate = new Date();
		Date toDate = new Date ();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 5, 18, 0, 0);//--> from date 
		fromDate = cal.getTime();
		
		cal.set(2019, 11, 26, 23, 40);//--> to date 
		toDate = cal.getTime();
		
		System.out.println("TRYING to choose the datee in range of  " + fromDate + "\n to " + toDate);
		resultEvents = socialEventRepository.filter15BetweenDate(fromDate, toDate);
		displayEventsShort(resultEvents);
		
		
		//START TEST  4
		//Those steps need to be done in each test . 
		System.out.println("Test 4 now I am tryng to filter with all together . .  ");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		
		
		eventTypes.remove(3);//eventTypes.remove(1);//eventTypes.remove(1);
		//areas.remove(3);areas.remove(1);areas.remove(0);
		//jewLvls.remove(1);
		
		int fromAge = 20 ; int toAge = 120;
		cal.set(2019, 1, 18, 0, 0);//--> from date 
		fromDate = cal.getTime();
		
		cal.set(2019, 11, 26, 23, 40);//--> to date 
		toDate = cal.getTime();
		

		System.out.println("cal ? + \n" + cal.get(1));
		cal =Calendar .getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		Date currentDate = cal.getTime();
		resultEvents = socialEventRepository.findByDateGreaterThanEqualOrderByDateAsc(currentDate);
		displayEventsShort(resultEvents);
		
		
		System.out.println("****************************LAST AND ACCURATE TEST : \n***************************");
		
		System.out.println("Filter  with those types : \n"  + eventTypes +  " \nand those areas : \n" + areas
				+  "\n and those jew level  : \n" + jewLvls 
				+ "\n within this range : " + fromAge + " to " + toAge
				+"\n in the dates : " + fromDate + " to " + toDate);
		
		
		System.out.println("First with just the multi props : ");
		resultEvents = socialEventRepository.filter10ByMultiProps(eventTypes, areas, jewLvls);
		displayEventsShort(resultEvents);
		
		System.out.println("Then with all : (ages and dates  ) ");
		resultEvents = socialEventRepository.filter16Main(eventTypes, areas, jewLvls, fromAge, toAge, fromDate, toDate);
		displayEventsShort(resultEvents);

		System.out.println("not work well -AGAIN - WITH DATES!! \n now it works.");
				
		/*
		resultEvents = socialEventRepository.filter15( fromDate, toDate);
		displayEventsShort(resultEvents);
		*/
		

		
		
		///END TEST
		

		
		

		
		
		
		if(true)return;
		
		/*
		//Copying MultiValues - not what u need. U need to generate List of MultiProps
		Map<String, List<String>> passedMultiValues = new TreeMap<>();
		
		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		
		passedMultiValues.remove(")
		*/
		
		
	}
	
	
	public void cleanTestFilter(){

		System.out.println("*******************TRYING filter 2 *****************************");
		List<SocialEvent> allEvents = (List<SocialEvent>)socialEventRepository.findAll();
		//get the initial Multi props as 3 maps
		//copy the lists and pass them as Map
		
		//on event type : 
		List<String> work = MULTI_PROPS_VALUES.get("eventType");
		Map<String, List<String>> mapWork = new TreeMap<>();
		mapWork.put("eventType", work);
		List<MultiPropValue> eventTypeInitial = new ArrayList<>(generateMultiValuesList(mapWork));
		
		//on jewLvlKeep: 
		 work = MULTI_PROPS_VALUES.get("jewLvlKeep");
		mapWork = new TreeMap<>();
		mapWork.put("jewLvlKeep", work);
		List<MultiPropValue> jewLvlKeepInitial =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		//on area: 
		 work = MULTI_PROPS_VALUES.get("area");
		mapWork = new TreeMap<>();
		mapWork.put("area", work);
		List<MultiPropValue> areaInitial =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		System.out.println("The initial Lists : \nEvent type =  " + eventTypeInitial  
				+ "\nJew Level Keep -  " + jewLvlKeepInitial 
				+"\nAreas - " + areaInitial);
		
		//generate 3 list to work with and they can get initials always : 
		List <MultiPropValue> eventTypes, jewLvls, areas;
		List <SocialEvent> resultEvents;
		
	
		
		
		//START TEST 4
		//Those steps need to be done in each test . 
		System.out.println("Test 4 NOW with JewLvlKeep :  same as before but filter also by jew Level keep");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		System.out.println("all events : \n" );
		displayEventsShort(allEvents);
		eventTypes.remove(2);//eventTypes.remove(0);
		areas.remove(3);areas.remove(0);//areas.remove(0);
		jewLvls.remove(0);
		
		
		
		
		
		int from = 55; int to = 70;
		
		
		System.out.println("TRYING to choose the with overlap range of  " + from + " to " + to);
		resultEvents = socialEventRepository.filter14(from, to);
		displayEventsShort(resultEvents);
		
		//creating dates to test
		Date fromDate = new Date();
		Date toDate = new Date ();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 5, 18, 0, 0);//--> from date 
		fromDate = cal.getTime();
		
		cal.set(2019, 11, 26, 23, 40);//--> to date 
		toDate = cal.getTime();
		
		
		
		
		//START TEST  4
		//Those steps need to be done in each test . 
		System.out.println("Test last now I am tryng to filter with all together . .  ");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		//eventTypes.remove(2);eventTypes.remove(1);eventTypes.remove(0);//->other test  - GAMES ONLY

		
		eventTypes.remove(3);//eventTypes.remove(1);//eventTypes.remove(1);//main test
		//areas.remove(3);areas.remove(1);areas.remove(0);
		//jewLvls.remove(1);
		
		int fromAge = 33 ; int toAge = 40;
		cal.set(2019, 1, 18, 0, 0);//--> from date 
		fromDate = cal.getTime();
		
		cal.set(2019, 11, 26, 23, 40);//--> to date 
		toDate = cal.getTime();
		

		System.out.println("cal ? + \n" + cal.get(1));
		cal =Calendar .getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		Date currentDate = cal.getTime();
		resultEvents = socialEventRepository.findByDateGreaterThanEqualOrderByDateAsc(currentDate);
		displayEventsShort(resultEvents);
		
		
		System.out.println("****************************LAST AND ACCURATE TEST :(filter 16 main) \n***************************");
		
		System.out.println("Filter  with those types : \n"  + eventTypes +  " \nand those areas : \n" + areas
				+  "\n and those jew level  : \n" + jewLvls 
				+ "\n within this range : " + fromAge + " to " + toAge
				+"\n in the dates : " + fromDate + " to " + toDate);
		
		
		
		System.out.println("Then with all : (ages and dates  ) ");
		resultEvents = socialEventRepository.filter16Main(eventTypes, areas, jewLvls, fromAge, toAge, fromDate, toDate);
		displayEventsShort(resultEvents);
		System.out.println("resultEvents = " + resultEvents);
//		System.out.println("resultEvents = " + resultEvents);

		///END TEST
		System.out.println("lingar test temp ");
//		System.out.println(socialEventRepository.tryQuery2("eventName", 51));
		
		System.out.println("Like Test last above just now areas will be null   ");
//		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		resultEvents = socialEventRepository.filter16Main(eventTypes, null, jewLvls, fromAge, toAge, fromDate, toDate);
		displayEventsShort(resultEvents);
		System.out.println("resultEvents = " + resultEvents);
		
		
		//Demonstration of the problem :
		System.out.println("This will work: ");
		List <SocialEvent>  resultEvents2 = socialEventRepository.filter16Main(eventTypes, null, jewLvls, fromAge, toAge, fromDate, toDate);
		System.out.println("resultEvents2 = " + resultEvents2);

		System.out.println("This shouldn't work, but does work: ");//When I do on web service - it's generate error 
		resultEvents2 = socialEventRepository.filter16Main(null, areas, jewLvls, fromAge, toAge, fromDate, toDate);
		System.out.println("resultEvents2 = " + resultEvents2);

		


		
		if(true)return;
		
	}
	
	public void testOfBugs(){
		System.out.println("This is new testing for the problem of the not refer to jewLvl.. need to do.");

		System.out.println("*******************TRYING filter 2 *****************************");
		List<SocialEvent> allEvents = (List<SocialEvent>)socialEventRepository.findAll();
		
		
		//get the initial Multi props as 3 maps
		//copy the lists and pass them as Map
		
		//on event type : 
		List<String> work = MULTI_PROPS_VALUES.get("eventType");
		Map<String, List<String>> mapWork = new TreeMap<>();
		mapWork.put("eventType", work);
		List<MultiPropValue> eventTypeInitial = new ArrayList<>(generateMultiValuesList(mapWork));
		
		//on jewLvlKeep: 
		 work = MULTI_PROPS_VALUES.get("jewLvlKeep");
		mapWork = new TreeMap<>();
		mapWork.put("jewLvlKeep", work);
		List<MultiPropValue> jewLvlKeepInitial =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		//on area: 
		 work = MULTI_PROPS_VALUES.get("area");
		mapWork = new TreeMap<>();
		mapWork.put("area", work);
		List<MultiPropValue> areaInitial =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		System.out.println("The initial Lists : \nEvent type =  " + eventTypeInitial  
				+ "\nJew Level Keep -  " + jewLvlKeepInitial 
				+"\nAreas - " + areaInitial);
		
		//generate 3 list to work with and they can get initials always : 
		List <MultiPropValue> eventTypes, jewLvls, areas;
		List <SocialEvent> resultEvents;
		
	
		
		
		//START TEST 4
		//Those steps need to be done in each test . 
		System.out.println("Test 4 NOW with JewLvlKeep :  same as before but filter also by jew Level keep");
		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		System.out.println("all events : \n" );
		displayEventsShort(allEvents);
		eventTypes.remove(2);//eventTypes.remove(0);
		areas.remove(3);areas.remove(0);//areas.remove(0);
		jewLvls.remove(0);
		
		
		
		
		
		int from = 55; int to = 70;
		
		
		System.out.println("TRYING to choose the with overlap range of  " + from + " to " + to);
		resultEvents = socialEventRepository.filter14(from, to);
		displayEventsShort(resultEvents);
		
		//creating dates to test
		Date fromDate = new Date();
		Date toDate = new Date ();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 5, 18, 0, 0);//--> from date 
		fromDate = cal.getTime();
		
		cal.set(2019, 11, 26, 23, 40);//--> to date 
		toDate = cal.getTime();
		
		
		
		
		//START TEST  4
		//Those steps need to be done in each test . 
		System.out.println("Test last now I am tryng to filter with all together . .  ");
		//define list of string with names 
//		List <String >work2 = new ArrayList<String>();
//		
//		//eventType
//		work2.add("meeting");
//		work2.add("games");
//
//		mapWork = new TreeMap<>();
//		mapWork.put("eventType", work2);
//		List<MultiPropValue> eventTypeTest =  new ArrayList<>(generateMultiValuesList(mapWork));
//		
//		
//		//keep level
//		work2 = new ArrayList<String>();//reset teh values
////		work2.add("shabbat");
//		work2.add("noShabbat");
//
//		mapWork = new TreeMap<>();
//		mapWork.put("jewLvlKeep", work2);
//		List<MultiPropValue> jewLvlKeepTest =  new ArrayList<>(generateMultiValuesList(mapWork));
//		
//		//area
//		work2 = new ArrayList<String>();
//		work2.add("south");
////		work2.add("noShabbat");
//
//		mapWork = new TreeMap<>();
//		mapWork.put("area", work2);
//		List<MultiPropValue> areaTest =  new ArrayList<>(generateMultiValuesList(mapWork));
//		
//		eventTypes = new ArrayList<>(eventTypeTest); jewLvls = new ArrayList<>(jewLvlKeepTest); areas= new ArrayList<>(areaTest);
//		
//		//eventType = [meeting, vaacation, speedate, games]
//		//jewLvlKeep = [shabbat, noShabbat]
//		//area = [jerusalem, north, south, center]
//
//		
//		
//		int fromAge = 3 ; int toAge = 120;
//		cal.set(2016, 1, 18, 0, 0);//--> from date 
//		fromDate = cal.getTime();
//		
//		cal.set(2022, 11, 26, 23, 40);//--> to date 
//		toDate = cal.getTime();
//		
//
//		System.out.println("cal ? + \n" + cal.get(1));
//		cal =Calendar .getInstance();
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		Date currentDate = cal.getTime();
//		resultEvents = socialEventRepository.findByDateGreaterThanEqualOrderByDateAsc(currentDate);
//		displayEventsShort(resultEvents);
		
		/*
		 * http://localhost:8080/ws/filter-try1?eventType=meeting&eventType=games&jewLvlKeep=noShabbat&area=south&agesRange=2&agesRange=120&datesRange=2018-12-07&datesRange=2025-09-08
		 */
		System.out.println("****************************LAST AND ACCURATE TEST :(filter 16 main) \n***************************");//workhere
		
		List <String >work2 = new ArrayList<String>();
		
		//eventType
		work2.add("meeting");
		work2.add("games");

		mapWork = new TreeMap<>();
		mapWork.put("eventType", work2);
		List<MultiPropValue> eventTypeTest =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		
		//keep level
		work2 = new ArrayList<String>();//reset teh values
//		work2.add("shabbat");
		work2.add("noShabbat");
		

		mapWork = new TreeMap<>();
		mapWork.put("jewLvlKeep", work2);
		List<MultiPropValue> jewLvlKeepTest =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		//area
		work2 = new ArrayList<String>();
		work2.add("south3");
//		work2.add("noShabbat");

		mapWork = new TreeMap<>();
		mapWork.put("area", work2);
		List<MultiPropValue> areaTest =  new ArrayList<>(generateMultiValuesList(mapWork));
		
		eventTypes = new ArrayList<>(eventTypeTest); jewLvls = new ArrayList<>(jewLvlKeepTest); areas= new ArrayList<>(areaTest);
		
		//eventType = [meeting, vaacation, speedate, games]
		//jewLvlKeep = [shabbat, noShabbat]
		//area = [jerusalem, north, south, center]

		
		
		int fromAge = 3 ; int toAge = 120;
		cal.set(2016, 1, 18, 0, 0);//--> from date 
		fromDate = cal.getTime();
		
		cal.set(2022, 11, 26, 23, 40);//--> to date 
		toDate = cal.getTime();
		

		System.out.println("cal ? + \n" + cal.get(1));
		cal =Calendar .getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		
		System.out.println("Filter last lingar  with those types : \n"  + eventTypes +  " \nand those areas : \n" + areas
				+  "\n and those jew level  : \n" + jewLvls 
				+ "\n within this range : " + fromAge + " to " + toAge
				+"\n in the dates : " + fromDate + " to " + toDate);
		resultEvents = socialEventRepository.filter16Main(eventTypes, areas, jewLvls, fromAge, toAge, fromDate, toDate);
		System.out.println("Events : ");
		displayEventsShort(resultEvents);
/*
		
		System.out.println("Then with all : (ages and dates  ) ");
		resultEvents = socialEventRepository.filter16Main(eventTypes, areas, jewLvls, fromAge, toAge, fromDate, toDate);
		displayEventsShort(resultEvents);
		System.out.println("resultEvents = " + resultEvents);
//		System.out.println("resultEvents = " + resultEvents);

		///END TEST
		System.out.println("lingar test temp ");
//		System.out.println(socialEventRepository.tryQuery2("eventName", 51));
		
		System.out.println("Like Test last above just now areas will be null   ");
//		eventTypes = new ArrayList<>(eventTypeInitial); jewLvls = new ArrayList<>(jewLvlKeepInitial); areas= new ArrayList<>(areaInitial);
		resultEvents = socialEventRepository.filter16Main(eventTypes, null, jewLvls, fromAge, toAge, fromDate, toDate);
		displayEventsShort(resultEvents);
		System.out.println("resultEvents = " + resultEvents);
		
		
		//Demonstration of the problem :
		System.out.println("This will work: ");
		List <SocialEvent>  resultEvents2 = socialEventRepository.filter16Main(eventTypes, null, jewLvls, fromAge, toAge, fromDate, toDate);
		System.out.println("resultEvents2 = " + resultEvents2);

		System.out.println("This shouldn't work, but does work: ");//When I do on web service - it's generate error 
		resultEvents2 = socialEventRepository.filter16Main(null, areas, jewLvls, fromAge, toAge, fromDate, toDate);
		System.out.println("resultEvents2 = " + resultEvents2);
*/
		


		
		if(true)return;
		
	}

}
