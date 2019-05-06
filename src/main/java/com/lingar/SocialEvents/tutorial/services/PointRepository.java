package com.lingar.SocialEvents.tutorial.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lingar.SocialEvents.tutorial.entities.Point;

public interface PointRepository extends PagingAndSortingRepository<Point, Long>{
	
	//INSERT INTO table_name (column1, column2, column3, ...)
	//VALUES (value1, value2, value3, ...);
	@Transactional
	@Modifying
	@Query(value = "insert into point (id, yoni, xania, word) Values (?1, ?2 , ?3, ?4) ", nativeQuery = true)
	void makeNewPoint(long id, int yoni, int xania, String word);


}
