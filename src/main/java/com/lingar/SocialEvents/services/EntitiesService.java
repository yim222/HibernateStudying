package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingar.SocialEvents.entities.LingarPropName;
import com.lingar.SocialEvents.entities.LingarPropValue;
import com.lingar.SocialEvents.entities.SinglePropName;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;

@Service
public class EntitiesService {
	private final SocialEventRepository socialEventRepository;
	private final SinglePropNameRepository singlePropNameRepository;
	private final SinglePropValueRepository singlePropValueRepository;
	private final LingarPropNameRepository lingarPropNameRepository;
	private final LingarPropValueRepository lingarPropValueRepository;

	
	//List of the contant properties
	public final String[] SINGLE_PROPS_VALUES = {"name", "address"};
	
	@Autowired //The default of what's happen when this is created. 
	public EntitiesService(SocialEventRepository socialEventRepository,
			SinglePropNameRepository singlePropNameRepository,
			SinglePropValueRepository singlePropValueRepository,
			LingarPropNameRepository lingarPropNameRepository,
			LingarPropValueRepository lingarPropValueRepository
			){
		
		System.out.println("hello I am EntitiesService");
		this.socialEventRepository = socialEventRepository;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		this.lingarPropNameRepository = lingarPropNameRepository;
		this.lingarPropValueRepository = lingarPropValueRepository;
		
		
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
	
	public void trying1(){
		
		//A list for work on and assign the right values
		List<LingarPropValue> lingarValuesList = new ArrayList<>();
		List<LingarPropValue> lingarValuesList2 = new ArrayList<>();
		
		Set<LingarPropValue> lingarValuesSet = new HashSet<LingarPropValue>();
		Set<LingarPropValue> lingarValuesSet2 = new HashSet<LingarPropValue>();


		LingarPropName lingarPropName1 = new LingarPropName("EventTypeLingar");
		lingarPropNameRepository.save(lingarPropName1);
		LingarPropValue lingarPropValue1 = new LingarPropValue(lingarPropName1, "lingar party ");
		LingarPropValue lingarPropValue2 = new LingarPropValue(lingarPropName1, "lingar trip ");
		lingarValuesList.add(lingarPropValue1);
		lingarValuesList.add(lingarPropValue2);
		
		lingarValuesSet.add(lingarPropValue1);
		lingarValuesSet.add(lingarPropValue2);


		lingarPropValueRepository.saveAll(lingarValuesList);
		///saving to event
		SocialEvent s1 = new SocialEvent();
		s1.setDescription("lingar event 1");
		//s1.setLingarValuesList(lingarValuesList);lingarValuesSet
		s1.setLingarValuesList(lingarValuesSet);
		socialEventRepository.save(s1);
		
		
		//saving another event		
		SocialEvent s2 = new SocialEvent();
		s2.setDescription("lingar event 2");
		s2.setLingarValuesList(lingarValuesSet);
		//s2.setLingarValuesList(lingarValuesList);
		//socialEventRepository.save(s2); - here it's make problem 
		s2.setLingarValuesList(lingarValuesSet);
		socialEventRepository.save(s2);
		
		
		LingarPropName lingarPropName2 = new LingarPropName("AreaLingar");
		lingarPropNameRepository.save(lingarPropName2);
		LingarPropValue lingarPropValue3 = new LingarPropValue(lingarPropName2, "lingar north ");
		LingarPropValue lingarPropValue4 = new LingarPropValue(lingarPropName2, "lingar south ");
		lingarPropValueRepository.save(lingarPropValue3);
		lingarPropValueRepository.save(lingarPropValue4);

		lingarValuesSet2.add(lingarPropValue3);
		lingarValuesSet2.add(lingarPropValue4);
		
		SocialEvent s3 = new SocialEvent();
		s3.setDescription("lingar event 3");
		s3.setLingarValuesList(lingarValuesSet2);
		socialEventRepository.save(s3);
		
		//Trying with Set of SEt
		lingarValuesSet2.addAll(lingarValuesSet);
		System.out.println(lingarValuesSet2);
		SocialEvent s4 = new SocialEvent();
		s4.setDescription("lingar event 4");
		s4.setLingarValuesList(lingarValuesSet2);
		socialEventRepository.save(s4);
	}
}
