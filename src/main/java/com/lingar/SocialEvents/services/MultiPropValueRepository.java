package com.lingar.SocialEvents.services;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.entities.MultiPropName;
import com.lingar.SocialEvents.entities.MultiPropValue;

public interface MultiPropValueRepository extends PagingAndSortingRepository<MultiPropValue, Long>{
	
	//find by multi_prop_name_id
	Set<MultiPropValue> findByMultiPropName(MultiPropName id);
	
	Set<MultiPropValue> findByMultiPropNameId(Long id);


}
