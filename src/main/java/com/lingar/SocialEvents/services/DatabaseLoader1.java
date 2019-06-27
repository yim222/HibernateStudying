package com.lingar.SocialEvents.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
		EntitiesService entitiesService = new EntitiesService(socialEventRepository, singlePropNameRepository,
				singlePropValueRepository, multiPropNameRepository, multiPropValueRepository);
		/*//Don't delete this! just comment: I pass this inside the createInitial it's more logic  
		if (singlePropNameRepository.count()<1){
			entitiesService.createInitialData();
		}
		*/
		entitiesService.createInitialData();
		
		//comment - if u want
		System.out.println("Stop the running for now... dataloader");
		entitiesService.create10EventsMock();
		entitiesService.displayEventsShort((List<SocialEvent>)socialEventRepository.findAll());
		//entitiesService.draftTrying();
		//entitiesService.tryingFilter();
		//entitiesService.tryingFilter2();//u here
		entitiesService.cleanTestFilter();
		
		
		if(true)return;//Stop the running - comment it if u want to ignore it
		
		entitiesService.trying1(); 
		entitiesService.trying2();
		System.out.println(socialEventRepository.findAll());
		System.out.println(multiPropNameRepository.findAll());
		entitiesService.trying3();
		//entitiesService.generateMultiValuesList(null);
		
		//System.out.println(socialEventRepository.findAll());

		


		
	}
}