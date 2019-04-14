package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingar.SocialEvents.entities.SinglePropName;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;

@Service
public class EntitiesService {
	private final SocialEventRepository socialEventRepository;
	private final SinglePropNameRepository singlePropNameRepository;
	private final SinglePropValueRepository singlePropValueRepository;
	
	//List of the contant properties
	public final String[] SINGLE_PROPS_VALUES = {"name", "address"};
	
	@Autowired //The default of what's happen when this is created. 
	public EntitiesService(SocialEventRepository socialEventRepository,
			SinglePropNameRepository singlePropNameRepository,
			SinglePropValueRepository singlePropValueRepository
			){
		
		System.out.println("hello I am EntitiesService");
		this.socialEventRepository = socialEventRepository;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		
		
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
		    
		    //Assign them both
		    SinglePropValue singlePropValue = new SinglePropValue(singlePropName, propValue);
		  //save it to database
            singlePropValueRepository.save(singlePropValue);
		    //Adding them to the list 
		    singleValuesList.add(singlePropValue);
		    
		    
		}
		
		System.out.println("singleValuesList:\n" + singleValuesList);
		
		return singleValuesList;
		
	}
	
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

	public void createSocialEvent(Map<String, String> values){
		
		List<SinglePropValue> singlePropValues = generateSingleValuesList(values);
		SocialEvent socialEvent = new SocialEvent("NA", "NA", 3, 4, singlePropValues);
		socialEventRepository.save(socialEvent);
		
	}

}
