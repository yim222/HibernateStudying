package com.lingar.SocialEvents.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lingar.SocialEvents.entities.SinglePropName;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;

@Component
public class DatabaseLoader1 implements CommandLineRunner {
	
	
	
	
	private final SocialEventRepository repository2;
	private final SinglePropNameRepository singlePropNameRepository;
	private final SinglePropValueRepository singlePropValueRepository;
	
	
	//First get the repository2 of the CRUD
	@Autowired
	public DatabaseLoader1(SocialEventRepository repository22,
			SinglePropNameRepository singlePropNameRepository,
			SinglePropValueRepository singlePropValueRepository
			
			
			) {
		//We create Repositoris to each entity - if U want a full control on the using. 
		this.repository2 = repository22;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		
	}

	//Then Do with that saving. 
	@Override
	public void run(String... strings) throws Exception {
		//We create new SinglePropName- U need to change it in each running because it's defined "Unique" and duplicates are not allowed
		SinglePropName singlePropName1 = new SinglePropName("other6");
		
		//We save it with the Repository
		singlePropNameRepository.save(singlePropName1);
		
		//The same about SinglePropValue - but here : 
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
	
		
	}
}