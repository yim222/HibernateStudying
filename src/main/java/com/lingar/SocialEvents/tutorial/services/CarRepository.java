package com.lingar.SocialEvents.tutorial.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.tutorial.entities.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{
	
	List<Car> findByManufacture(String manufac);
	
	
	//find by owner and manufacture
	Car findByOwnerAndManufacture(String owner, String manufacture);
	boolean existsByOwnerAndManufacture(String owner, String manufacture);
	
	
	//find distinct
	List<Car> findDistinctByPriceGreaterThan(int bound);
	
	//without find distinct
	List<Car> findByPriceGreaterThan(int bound);

	//with order of name desc 
	List<Car> findByPriceGreaterThanOrderByOwnerDesc(int bound);
	
	//find by owner and ignore case 
	Car findByOwnerIgnoreCase(String owner);
	
	//get , query - is the same :
	Car getByOwner(String owner);
	
	//get ,read,  query , find, stream - is the same :
	Car queryByOwner(String owner);
	
	Car findByOwner(String owner);
	
	Car streamByOwner(String owner);
	
	Car readByOwner(String owner);
	
	Car findBySameField(String same);
	
	int countByPriceLessThan(int max);
	
	//find greater than the passed parameter : 
	List<Car> findByYearGreaterThan(Date date);
	
	//find greater than the passed parameter : 
	List<Car> findByYearGreaterThanEqual(Date date);
	
	//find cars in range of price (include) 
	List<Car> findByPriceBetween(int min, int max);
	
	//find cars in range of price (include) 
	List<Car> findByPriceBetweenOrderByPriceAsc(int min, int max);
	
	//existByCusom
	//boolean existsByCustomName(int x);
	
	//It won't work because this property is called different. u need the real name of it
	boolean existsByOtherName(int x);
	
	//Work on field of the nested entity : 
	Car findByPointXania(int x);
	
	//Get only from specific page
	Slice<Car> findByManufacture(String manu, Pageable p);
		
	
	Page<Car> findByOwner(String o, Pageable pageable);
	
	//make limit 
	
	List<Car> findFirst3ByPriceLessThan(int max);
	
	List<Car> findTop3ByPriceLessThan(int max);
	
	List<Car> findLast3ByPriceLessThan(int max);
	
	
	//Find owner that start with the par
	List<Car> findByOwnerIsStartingWith(String word );

	
	//Find every entity that one of the column contain the par  
	//List<Car> findAllByContains(String word);
	List<Car> findByOwnerContains(String word);
	
	
	//List<Car> findAllByIsStartingWith(String word);//->This is not work
	
	List<String >findOwnerByManufacture(String manu);
	
	//Find all order by order
	List<Car> findAllByOrderByOwnerAsc();
	
	//List<Car> findAllByLikeByIsStartingWith(String str); //Not work too
	

	

	


	
	
	
}
