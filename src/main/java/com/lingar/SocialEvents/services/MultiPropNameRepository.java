package com.lingar.SocialEvents.services;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.entities.MultiPropName;


public interface MultiPropNameRepository extends PagingAndSortingRepository<MultiPropName, Long>{
	
	/**CLEAN WORK */
	
	boolean existsByMultiName(String multiName);
	
	/**END OF CLEAN WORK */
	
	//List<MultiPropName> findByMultiName2(String multiName2); //Here u cerate queries u need to write the findByAccurateNameProp
	//U can define the type as list of the object, the object itself or String
	List<MultiPropName> findByMultiName(String multiName2);
	
	//U can do it with exists too. and the parameter name is not important.
	//boolean existsByMultiName(String foo);
}
