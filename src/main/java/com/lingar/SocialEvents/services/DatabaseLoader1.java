package com.lingar.SocialEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



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

		entitiesService.trying1(); 
		
		//entitiesService.generateMultiValuesList(null);
		


		
	}
}