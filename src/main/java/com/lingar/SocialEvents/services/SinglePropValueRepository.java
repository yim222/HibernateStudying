package com.lingar.SocialEvents.services;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.entities.SinglePropValue;

public interface SinglePropValueRepository extends PagingAndSortingRepository<SinglePropValue, Long>{
	
	List<String> findBySinglePropNamePropName(String propName);
	//List<String> findBySinglePropNamePropNameAndSocialEvnetId(String propName, long id);
	//SinglePropValue findBySinglePropNamePropNameFrom(List <SinglePropValue> list, String propName);

	
}
