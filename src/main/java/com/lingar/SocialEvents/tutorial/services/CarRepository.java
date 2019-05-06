package com.lingar.SocialEvents.tutorial.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	
	/**Named query = with annotations */
	
	
	//This is manually named query: 
	List<Car> findByLingarMethod(String name);
	
	
	//@Query("select n from Car u where n.manufacture = ?1")//see the nEntities definition Car = /*The domain wanted*/ ?1 - it mean the parameter
	//List<Car> namedQuery1(String manufacture);
	// The pattern : "select x(=something like entity/ie from car ) from DomainName (=like Car) x where x.field = ?1 // I yet understand the ?1 what is that represent, 
	//Didn't get satisfied explain til now. 
	@Query("select u from Car u where u.price = ?1")// -position-based parameter binding, it's mean that by the position in the args it will be read
	Car findByPrice(int price);
	
	
	@Query("select u from Car u where u.price >= ?1")
	List<Car> findBBBPrice(int price);
	
	@Query("select u from Car u where u.manufacture = ?2")
	List <Car> nowItsWork(String manu);
	
	@Query("select u from Car u where u.owner like %?1")//if the %before the par it's take the end. and if before check if stating with. Why ? 
  	List<Car> findAllOwnersEndsWith(String w);
	
	@Query("select u from Car u where u.owner like ?1%")//if the %before the par it's take the end. and if before check if stating with. Why ? 
  	List<Car> findAllOwnersStartsWith(String w);
	
	/**This is with native SQL and some paramters*/
	//@Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)- from the doc
	//@Query(value = "SELECT * FROM Cars WHERE manufacture = ?1 OR manufacture = ?2 ",

	@Query(value = "SELECT * FROM Car WHERE manufacture = ?1 ", 
			nativeQuery = true)//U can have differences in the columns name also because
	//In JPQL it's in camel capatalize and in the native it's with underscore separated.
	List<Car> withNativeSql(String manu1);
	
	@Query(value = "SELECT * FROM Car WHERE manufacture = ?1 OR manufacture = ?2 ",
			nativeQuery = true)//U can have differences in the columns name also because
	//In JPQL it's in camel capatalize and in the native it's with underscore separated.
	List<Car> withNativeSql2(String manu1, String manu2);
	
	//Naative With using pagination
	@Query(value = "SELECT * FROM CAR WHERE PRICE <= ?1",
		    countQuery = "SELECT count(*)  FROM CAR WHERE PRICE >= ?1",
		    nativeQuery = true)
	Page<Car> pagingWithNative(int max , Pageable pageable);
	
	
	//Jpql with pagination - not work 
	/*
	@Query(value = "select e from Car e where e.price => ?1", 
			countQuery = "select e.count from Car e where e.price => ?1")
	Page<Car> pagingwithJpql(int min, Pageable pageable);
*/
	//Naative With using pagination
	/*
		@Query(value = "SELECT * FROM CAR WHERE PRICE <= ?1",
			    countQuery = "SELECT count(*)  FROM CAR WHERE PRICE >= ?1",
			    nativeQuery = true)
		Page<Car> pagingWithNative2(int max , Pageable pageable);
	*/
	/*
	@Query( "select e from Car e where e.price => ?1")
	Page<Car> pagingwithJpql(int min, Pageable pageable);
	*/
	
	@Query("select u from Car u where u.owner like ?1%")
	List<Car> findByAndSort(String lastname, Sort sort);
	
	
	//Query with Named params. 
	@Query("select u from Car u where u.manufacture = :man1 or u.price >= :price2")
  	List<Car >withNamedParamsManufactureAndMin(@Param("price2") int otherPrice,
	                                 @Param("man1") String otherMan1);
	
	//@Query("select u from #{#entityName} u where u.lastname = ?1")
	@Query("select u from #{#entityName} u where u.price < ?1")
	List<Car> withSpEL(int price);
	
	@Query(value = "Select price from Car where price < ?1 ", nativeQuery = true)
	List<String> tryNative(int price);
	
	@Query(value = "Select price , owner from Car where price < ?1 ", nativeQuery = true)
	List<String[]> tryNative2(int price);
	
	//Update
	@Transactional
	@Modifying
	@Query("update Car u set u.changeMe = ?1 where u.price >= ?2")
	int setFixedFirstnameFor(String changeMe, int min );
	
	
	//void setOwnerFor(String owner, String other);
	@Transactional
	void deleteByPointId(long pointId);
	
	@Transactional
	void deleteByPrice(int price);
	


}
