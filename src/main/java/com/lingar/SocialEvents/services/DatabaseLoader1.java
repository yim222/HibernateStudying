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
		this.repository2 = repository22;
		this.singlePropNameRepository = singlePropNameRepository;
		this.singlePropValueRepository = singlePropValueRepository;
		
	}

	//Then Do with that saving. 
	@Override
	public void run(String... strings) throws Exception {
		SinglePropName singlePropName1 = new SinglePropName("other22");
		singlePropNameRepository.save(singlePropName1);
		SinglePropValue singlePropValue1 = new SinglePropValue(singlePropName1, "lingar44" );
		SinglePropValue singlePropValue2 = new SinglePropValue(singlePropName1, "Yosisss" ); 
		
		
		

		singlePropValueRepository.save(singlePropValue1);
		singlePropValueRepository.save(singlePropValue2);
		
		SinglePropName singlePropName2 = new SinglePropName("address2");
		
		
		
		//https://stackoverflow.com/a/50186140/9727918
		Optional <SinglePropName> dbData1 =  singlePropNameRepository.findById(4L);
		SinglePropName singlePropName3 = dbData1.get();
		System.out.println(singlePropName3.getPropName());
		SinglePropValue singlePropValue3 = new SinglePropValue(singlePropName3, "New event" );
		singlePropValueRepository.save(singlePropValue3);
		List<SinglePropValue> singleValuesList = new ArrayList<>();
		SinglePropValue singlePropValueEl1 = new SinglePropValue(singlePropName3, "Trip" );
		dbData1 =  singlePropNameRepository.findById(1L);
		SinglePropName singlePropName4 = dbData1.get();
		SinglePropValue singlePropValueEl2 = new SinglePropValue(singlePropName4, "Holon" );
		singlePropValueRepository.save(singlePropValueEl1);singlePropValueRepository.save(singlePropValueEl2);
		singleValuesList.add(singlePropValueEl1);
		singleValuesList.add(singlePropValueEl2);
		
		//creating new socEvent
		//SocialEvent(String description, String moreValue, int fromAge, int toAge, List<SinglePropValue> singleValuesList)
		SocialEvent socialEvent1 = new SocialEvent("desc","bla", 3, 4,  singleValuesList);
		
		//save it 
		repository2.save(socialEvent1);
		
		
		
		
	///singlePropValueRepository.save(singlePropValue3);
		
	}
}