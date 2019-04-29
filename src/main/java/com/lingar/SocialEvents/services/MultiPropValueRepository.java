package com.lingar.SocialEvents.services;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lingar.SocialEvents.entities.MultiPropName;
import com.lingar.SocialEvents.entities.MultiPropValue;

public interface MultiPropValueRepository extends PagingAndSortingRepository<MultiPropValue, Long>{
	
	//find by multi_prop_name_id
	Set<MultiPropValue> findByMultiPropName(MultiPropName id);
	
	Set<MultiPropValue> findByMultiPropNameId(Long id);
	
	MultiPropValue findByPropValue(String propValue);
	
	//boolean exists(String propValue, long multiPropNameId);
	/*
	@Query(nativeQuery = true, value = "select count(e)>0 from MultiPropValue e  where e.multiPropNameId = :propNameId "
			+ "AND e.propValue = :propValue"
			+ "")
			*/
	/* - not work 
	@Query( "select count(e)>0 from MultiPropValue e  where e.multiPropNameId = :propNameId "
			+ "AND e.propValue = :propValue"
			+ "")
	
	boolean existsIfBlaBla(@Param ("propValue" ) String propValue, @Param("propNameId") long propNameId);
		*/
	


}
