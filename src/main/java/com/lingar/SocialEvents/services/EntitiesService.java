package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingar.SocialEvents.entities.MultiPropName;
import com.lingar.SocialEvents.entities.MultiPropValue;
import com.lingar.SocialEvents.entities.SinglePropName;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;

@Service
public class EntitiesService {
	private final SocialEventRepository socialEventRepository;
	private final SinglePropNameRepository singlePropNameRepository;
	private final SinglePropValueRepository singlePropValueRepository;
	private final MultiPropNameRepository multiPropNameRepository;
	private final MultiPropValueRepository multiPropValueRepository;

	
	//List of the contant properties
	public final String[] SINGLE_PROPS_VALUES = {"name", "address"};
	
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
		SinglePropName singlePropName = new SinglePropName();
		for(String propName : SINGLE_PROPS_VALUES ){
			
			singlePropName = new SinglePropName(propName);
			singlePropNameRepository.save(singlePropName);
			
		}
		
		System.out.println("All Entities saved. ");
		
	}
	
	/**PREPARED*/
	public void createSocialEvent(Map<String, String> values){
		
		List<SinglePropValue> singlePropValues = generateSingleValuesList(values);
		SocialEvent socialEvent = new SocialEvent();
		socialEvent.setSinglePropsValuesList(singlePropValues);
		socialEventRepository.save(socialEvent);
		
	}
	
	/**PREPARED*/
	public Set<MultiPropValue> generateMultiValuesList(Map<String, List<String>> values){
		
		System.out.println("*****************************START - trying new generate Multi values list******************************** ");
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
		
		
		System.out.println("*****************************END - trying new generate Multi values list******************************** ");

		return multiPropValues;
		
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
		s1.setLingarValuesList(multiValuesSet);
		socialEventRepository.save(s1);
		
		
		//saving another event		
		SocialEvent s2 = new SocialEvent();
		s2.setLingarComment("multi event 2");
		s2.setLingarValuesList(multiValuesSet);
		//s2.setMultiValuesList(multiValuesList);
		//socialEventRepository.save(s2); - here it's make problem 
		s2.setLingarValuesList(multiValuesSet);
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
		s3.setLingarValuesList(multiValuesSet2);
		socialEventRepository.save(s3);
		
		//Trying with Set of SEt
		multiValuesSet2.addAll(multiValuesSet);
		System.out.println(multiValuesSet2);
		SocialEvent s4 = new SocialEvent();
		s4.setLingarComment("multi event 4");
		s4.setLingarValuesList(multiValuesSet2);
		socialEventRepository.save(s4);
		
		System.out.println("Trying MultiPropValuesGenerator : ");
		
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
}
