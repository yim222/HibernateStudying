package com.lingar.SocialEvents.tutorial.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Service;

import com.lingar.SocialEvents.tutorial.entities.Car;

@Service
public class TutorialService {
	
	private CarRepository carRepo;
	private PointRepository pointRepo;
	@Autowired
	public TutorialService (CarRepository carRepo, PointRepository pointRepo){
		this.carRepo = carRepo;
		this.pointRepo = pointRepo;
	}
	
	public void creating10Cars(){
		Calendar cal = Calendar.getInstance();
		cal.set(2008, 5, 11);
		
		carRepo.save(new Car("Avi" , "Honda" , cal.getTime(), 500));
		cal.set(2018, 3,23);
		
		carRepo.save(new Car("Mosher" , "Volvo" , cal.getTime(), 200));
		cal.set(2012, 8,23);
		carRepo.save(new Car("Izik" , "Ford" , cal.getTime(), 246));
		cal.set(2014, 3,23);
		carRepo.save(new Car("jhon" , "Mazda" , cal.getTime(), 420));
		cal.set(1997, 3,23);
		carRepo.save(new Car("Jacob" , "Toyota" , cal.getTime(), 135));
		cal.set(2010, 9,11);
		carRepo.save(new Car("Rivki" , "Honda" , cal.getTime(), 453));
		cal.set(2011, 3,23);
		carRepo.save(new Car("Yosi" , "Subaro" , cal.getTime(), 637));
		cal.set(2017, 3,23);
		carRepo.save(new Car("Miryam" , "Mazda" , cal.getTime(), 156));
	}
	
	public void studyingPreDefinedQueries(){
		System.out.println("The following queries are of the predefined queries : \n"
				+ " https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts");
		
		
		System.out.println("Save is very basic u must know it ");
		Calendar cal = Calendar.getInstance();
		
		Car newCar = new Car ("Lingar", "Honda",cal.getTime(), 1000);
		carRepo.save(newCar);
		System.out.println("to see all u have findAll. It's need to be list or other Iterable (need to study deeply that) \n "
				+ "U need to make cast. See here the code ");
		ArrayList<Car> cars1 = (ArrayList)carRepo.findAll();
		System.out.println(cars1);
		System.out.println("Let's get the Id of that . Because u need it in those built methods . "
				+ "\nSee How cool it that it generate the id by tself. ID = \n " + newCar.getId());
		
		UUID carId = newCar.getId();
		
		System.out.println("Now I can return it by the ID only(It's returned as optional. \n: "
				+ "U can study on that here \n- https://www.tutorialspoint.com/java8/java8_optional_class.htm \n: "
				+ "Also, when U use UUID (as here ) U need to define the @columns (length =16) . See the Id field at Car");
		System.out.println(carId);
		//System.out.println(carRepo.findById(carId));
		Optional<Car> returnedCar = carRepo.findById(carId);
		System.out.println(returnedCar.get());
		long entitiesAmt = carRepo.count();
		System.out.println("The number of all the entities :  " + entitiesAmt);
		
		boolean trueOrFalse = carRepo.existsById(carId);
		System.out.println("Check by id if exist. new car exist ? - " + trueOrFalse);
		
		System.out.println("Now I delete this car : " + returnedCar + "\nBy its Id and we check again the list : "
				+ "(U can do it with deleteById too. See inside and uncomment (because if it's emty spring throws exception) ");
		//carRepo.delete(newCar);
		carRepo.deleteById(carId);

		System.out.println("But before - notice that the changed that happen , don't affected on the data we get before. This is the list from before : ");

		System.out.println(cars1);
		
		System.out.println("U can see it's the same . now I will get the updated data and look for this car and see it's gone - ");
		
		cars1 = (ArrayList)carRepo.findAll();
		System.out.println(cars1);
		entitiesAmt = carRepo.count();
		System.out.println("The number of all the entities :  " + entitiesAmt);
		trueOrFalse = carRepo.existsById(carId);
		System.out.println("Check by id if exist. new car exist ? - " + trueOrFalse);
		
		//Do one example witht the Save all or like that and do the methods of pagingAndSorting. And commented example of deleteAll .
		
		System.out.println("U can use in save/delete/find etc. with All . little example. \n this is the reference : "
				+ "https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html ");
		
		System.out.println("U need to control the ITerable. I think it's something like arraylist and more .\n "
				+ "All what u see here as implemented classes - https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html");
		
		System.out.println("Create two entities : ");
		//cal.set(2012, 8,23);
		cal.set(2013,6,25);
		
		// Car(String owner, String manufacture, Date year, int price)
		Car car2 = new Car ("Koni", "Ford", cal.getTime(), 178);
		cal.set(2007,6,12);
		Car car3 = new Car ("Amir", "Subaro", cal.getTime(), 367);
		System.out.println("Those the cars, before saving them :  \n1- " + car2 + "\n2- "+car3);
		
		System.out.println("Inserting them to ArrayList<Car>...");
		
		ArrayList<Car> cars2 = new ArrayList<>();
		cars2.add(car2);
		cars2.add(car3);
		
		System.out.println("Saving them with saveAll(Iterable)");
		carRepo.saveAll(cars2);
		System.out.println("now let's see all entities : \n" + carRepo.findAll());
		
		System.out.println("I can delete them too with such method : ");
		
		carRepo.deleteAll(cars2);
		System.out.println("now let's see all entities : \n" + carRepo.findAll());
		
		System.out.println("U can also make deleteAll() to all , Just uncomment that \n "
				+ "But consider that u will nothing on the DB after that, and no need to say that it's very danger (to data) method. ");
		//carRepo.deleteAll();
		System.out.println("Now let's learn about two method at  the subinterfce PagingAndSorting : \n "
				+ "https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html");
		
		System.out.println("I don't get down to the page and Sort options. it's need also learning .");
		
		System.out.println("The additional methods here is : \n "
				+ "1- Iterable<T> findAll(Sort sort); \n "
				+ "2- Page<T> findAll(Pageable pageable);");
		
		System.out.println("findAll(Sort sort); help u to get by sort the entities  . \n "
				+ "https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Sort.html \n "
				+ "For example : \n");
		
		System.out.println("In the string u inster the fields U want to it's affect. U cant insert not exist property It will generate exception");
		List<String> forSort = new ArrayList<String>();
		forSort.add("owner");
		//forSort.add("BBB");
		Sort sort1 = new Sort(Sort.Direction.ASC, forSort) ;
		System.out.println(sort1);
		
		System.out.println("Now I am trying to get it : ");
		
		System.out.println(carRepo.findAll(sort1));
		
		System.out.println("Trying to see how it work with some words propery. Is camel capatalize or as it's written or both : ");
		List<String> forSort2 = new ArrayList<String>();
		forSort2.add("threeWordsProperty");
		
		sort1 = new Sort(Sort.Direction.DESC, forSort2) ;
		cars2 = (ArrayList<Car>)carRepo.findAll(sort1);
		System.out.println(cars2);
		System.out.println("It's work with camelCapatlize.");
		List<String> forSort3 = new ArrayList<String>();
		//forSort3.add("three_words_property");
		//sort1 = new Sort(Sort.Direction.ASC, forSort3) ;
		cars2 = (ArrayList<Car>)carRepo.findAll(sort1);
		System.out.println(cars2);
		System.out.println("It won't work with db_convetion. Try to uncomment above and see. ");
		
		System.out.println("If i add sort to by another property (like owner. That's what I get. I Don't dig into that ");
		forSort2.add("owner");
		sort1 = new Sort(Sort.Direction.DESC, forSort2) ;
		
		System.out.println("So sort it's way to add order to the query. Like order.by in SQL ");
		cars2 = (ArrayList<Car>)carRepo.findAll(sort1);
		System.out.println(cars2);
		System.out.println("Is there is a change ? not. But if I will add two with same name and the owner is first and the price is second. "
				+ "So he will go after the second order.by I think. Lets try. ");
		
		cal.set(1999, 05, 26);
		
		Car car4 = new Car ("Roni", "Subaro", cal.getTime(), 354);
		cal.set(2007, 05, 26);

		Car car5 = new Car ("Mosh", "Honda", cal.getTime(), 279);
		
		carRepo.save(car4);
		carRepo.save(car5);
		
		System.out.println("2 entities added to the DB : \n" + carRepo.findAll());
		
		System.out.println("Doing sort order by first - manufacture, then owner (if the manufacture is equal):\n");
		List<String> forSort4 = new ArrayList<String>();
		forSort4.add("manufacture");
		forSort4.add("owner");
		sort1 = new Sort(Sort.Direction.ASC, forSort4);
		System.out.println(carRepo.findAll(sort1));
		
		System.out.println("Now about the paging. \n those are the three objects U need to learn about paging : \n"
				+ "https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html , https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html, https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/PageRequest.html");
		
		int pageEntities = 2; int pageIndx = 3;
		int pagenumebr = pageIndx+1;
		System.out.println("And here U can see - for example page number " +(pageIndx+1) + " when each page has " + pageEntities
				+ " entities. (U can play with the code here )" );
		Page<Car> cars3 = carRepo.findAll(PageRequest.of(pageIndx,pageEntities));
		System.out.println(cars3.getContent() + "\n" + cars3.getNumber());
		System.out.println("U can check it with http://localhost:8080/api/cars?page=3&size=2 for example");
		
		System.out.println("\n************* END of the PREDEFINED methods*************************\n");

		
		

		
		
		
		
		
	}
	
	
	
	public void studyingQueries1(){
		
		System.out.println("\n*****************STUDYING QUERIES 1 - BASIC OF NAMED METHODS QUERIES *****************************************\n");

		
		System.out.println("Here I get the entities on certain page (pageNumberStratingFrom0, page size ) \n"
				+ "U have many methods in the Page object ");
		Page<Car> cars = carRepo.findAll(PageRequest.of(1,5));
		System.out.println(cars.getContent() + "\n" + cars.getNumber());
		List<Car> cars2 = carRepo.findByManufacture("Honda");
		System.out.println(cars2);
		
		
		System.out.println("Here I connect two field - owner, and manufacture. It's derived queries, I have do it in the car repo : ");
		Car car1 = carRepo.findByOwnerAndManufacture("Rivki", "Honda");
		System.out.println(car1);
		
		System.out.println("Here I did it with exist too ");
		
		String owner = "Rivki";
		String manufacture = "Honda";
		boolean b ;
		System.out.println("Is " + owner + " of  " + manufacture + " exist ? ");
		
		
		b = carRepo.existsByOwnerAndManufacture(owner, manufacture);
		System.out.println(b);
		
		owner = "Rivkil";
		
		System.out.println("Is " + owner + " of  " + manufacture + " exist ? ");
		
		
		b = carRepo.existsByOwnerAndManufacture(owner, manufacture);
		System.out.println(b);
		
		int minPrice = 300;
		System.out.println("Found the cars above " + minPrice);
		System.out.println(carRepo.findDistinctByPriceGreaterThan(minPrice));
		
		System.out.println("Found the cars above " + minPrice + " without distinct (don't find the different");

		System.out.println(carRepo.findByPriceGreaterThan(minPrice));
		
		System.out.println("Found the cars above " + minPrice + " with order descent by owner");

		System.out.println(carRepo.findByPriceGreaterThanOrderByOwnerDesc(minPrice));
		
		System.out.println("Get car by owner and Ignore case (but seems that it's default in the queries )");
		
		System.out.println(carRepo.findByOwnerIgnoreCase("AVI"));
		
		System.out.println("Find, Stream, Get, Query , read is the same : ");
		System.out.println(carRepo.getByOwner("AVI"));
		System.out.println(carRepo.findByOwner("AVI"));
		System.out.println(carRepo.queryByOwner("AVI"));
		System.out.println(carRepo.streamByOwner("AVI"));
		System.out.println(carRepo.readByOwner("AVI"));
		int maxPrice = 200;
		System.out.println("Count by less then : " + maxPrice);
		
		System.out.println(carRepo.countByPriceLessThan(maxPrice));
		
		System.out.println("U here - in the delete it's also true - check it  ");
		
		System.out.println("Great, now I am trying to make filter by date. ");
		Calendar cal = Calendar.getInstance();
		cal.set(2005,6 ,11);
		  
		System.out.println(carRepo.findByYearGreaterThan(cal.getTime()));
		
		System.out.println("Here I make differnets between greater and greater and equal. For that I need an accurate date (that includes time too )");
		
		Car c = carRepo.findByOwner("Avi");
		System.out.println("this is the car : " + c);
		
		
		System.out.println("So this is greater than the car date: \n" + carRepo.findByYearGreaterThan(c.getYear()));
		
		System.out.println("And this is greater than or equal the car date: \n" + carRepo.findByYearGreaterThanEqual(c.getYear()));
		
		System.out.println("And for last - see how I found range of prices : ");
		
		int minPrice1 = 135; int maxPrice1 = 500;
		
		System.out.println(carRepo.findByPriceBetween(minPrice1, maxPrice1));
		
		System.out.println("And, with asceding order : \n " + carRepo.findByPriceBetweenOrderByPriceAsc(minPrice1, maxPrice1) ); 
		System.out.println("u can see many avialabe Criterias(as properties exapression )  here :\n https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation");
		System.out.println("If u get entity that doesn't exist findBy won't throw exception - \n" + carRepo.findByOwner("sadadsda"));
		System.out.println("But if u ask something that return 1 Car but there are a lot, it will throw exception if there will be duplicate \n "
				+ "Need to know how to get over it. For example uncomment here :\n"
				+ "U get excpetion : query did not return a unique result: 10 -the amount of the duplicates entities with that criteria. \n "
				+ "The basic handling is not to define like that field that can be duplicated.  ");
		System.out.println(carRepo.findBySameField("not same"));

		//System.out.println(carRepo.findBySameField("same")); // -UNCOMMENT HERE FOR GENERATE THE EXCEPTIONS

		System.out.println("\n*****************END OF STUDYING QUERIES 1 - BASIC OF NAMED METHODS QUERIES *****************************************\n");
	}
	
	
	public void studyingQueries2(){
		System.out.println("\n****************************STUDYING QUERIES 2 - ADVANCED ISSUES AT DERIVED NAMED METHODS QUERIES**********************\n");
		

		
		System.out.println("U must define the named query with the Java bean name, event if u change the property name with @Column (name = ... ) "
				+ "\nFor example here I check if existsByOtherName, even that in the entity it name is custom_name");
		int i = 3;
		System.out.println("is " + i + " exists ? \n"+ carRepo.existsByOtherName(i));
		
		System.out.println("Trying to learn that : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions");
		System.out.println("I think it's about other entities in the entity ");
		
		System.out.println("First I am tryin to add other entity to the car. I apparently need to save it before. So I will use annotation with Types. ");
		
		System.out.println("So I annoted it with @oneToOne and defined its cascade type and its work . ");
		
		
		System.out.println("So U indeed can affect on the nested entity field. If U declate it that after that.\n  "
				+ "Here U can see that I access the Point.xania field. Because it's not exist at the main entity. ");
		
		int i2 = 3;
		
		//System.out.println("is Point.xania is " +i2  + "in the stock ? " +  carRepo.findByPointXania(3)); // -> can cause problems if there are more than 1. 
		
		System.out.println("Trying to update ?");
		System.out.println("Seems it's possible to save again just if u changed the values, and then to save but if not - its throw execption. \n"
				+ "U can try yo uncomment here the change and see what's happen ");
		Car car1 = carRepo.getByOwner("Avi");
		System.out.println(car1);
		
		car1.setOwner("Lingari");
		
		carRepo.save(car1);
		System.out.println(carRepo.findAll());
		
		System.out.println("U can define parameter of Sort or Paging in the Repository. I did a little example. It's dicussed here : \n"
				+ "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.special-parameters");
		
		String manu = "honda"; int pageNum = 2; int entitiesAmt = 2;
		
		System.out.println("Find from manufacture " + manu + "Only from page number " + pageNum + " when U have "
				+ entitiesAmt + " entities on each page");
		Page<Car> cars1 ;
		Slice<Car> cars2 ;
		
		cars2 = carRepo.findByManufacture(manu, PageRequest.of(pageNum-1, entitiesAmt));
		System.out.println(cars2.getContent());
		System.out.println("Seems like its do page from the criteria, not from all and then select. ");
		
		System.out.println("U can also make limitation with find first/top(they the same) : ");
		int i3 = 400;
		System.out.println("Show the first and the top 3 less than " +i3);
		System.out.println("first:\n" + carRepo.findFirst3ByPriceLessThan(i3));
		System.out.println("Top:\n" + carRepo.findTop3ByPriceLessThan(i3));
		System.out.println("Last - not work proper..:\n" + carRepo.findLast3ByPriceLessThan(i3));
		
		System.out.println("Anyway - it's just take the firsts. ");
		
		System.out.println("U can define also queries with wildcards. It's mean U can say if it's contain start with and more. \n"
				+ "See here about JPA : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.sample-app.finders.strategies \n "
				+ "and here about the SQL : https://www.w3schools.com/sql/sql_wildcards.asp");
		
		String searchWord = "j";
		
		System.out.println("For example - here I search in the owners who start with : " + searchWord);
		
		System.out.println(carRepo.findByOwnerIsStartingWith(searchWord));
		
		searchWord = "v";
		//System.out.println("And here I am looking in all property who is contain " + searchWord +"\n but it's not work. Don't understand How to affect on findAll. ";
		
		//System.out.println(carRepo.findAllContains(searchWord));
		System.out.println(carRepo.findByOwnerContains(searchWord));

		System.out.println("Intersting to check if it's possible also on the nested entities and on other types (it's good thing for general filter) ");

		//carRepo.findAll()
		
		System.out.println("U can't do expression with findAll. I think. I think because u need to define on what the expression rule ");
		
		System.out.println(carRepo.findAllByOrderByOwnerAsc());
		
		
		System.out.println("Is U can to bring only on property of entity, and not all ? -NOT ");
		
		System.out.println(carRepo.findOwnerByManufacture("Honda"));
		
		System.out.println("Seemed that it's not work well. It's send the entities as String but all data . ");
		
		
		
		
		
		
		System.out.println("\n****************************END OF STUDYING QUERIES 2 - ADVANCED ISSUES AT NAMED METHODS QUERIES**********************\n");

	}
	
	
	public void studyingQueries3(){
		
		System.out.println("\n****************************STUDYING QUERIES 3 - ADVANCED ISSUES AT NOT DERIVED NAMED METHODS QUERIES**********************\n");
		
		System.out.println("TO see the refernece of the annotation open it in eclipse by click+ctrl on that or that : \n : "
				+ "https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/Query.html");
		System.out.println("Using JPA Named Queries - \n "
				+ "\nwith @NamedQuery(...)"
				+ "\nThis query is declared at top of the Car Object, and defined in the interface. \n"
				+ "Let's check if it works. \n"
				+ "see here - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.named-queries ");
		
		System.out.println(carRepo.findByLingarMethod("Rivki"));
		
		System.out.println("Using @Query - \n"
				+ "With that u can use the @query on the method that U write(define) at the interface of repository, "
				+ "\nwithout need to define them above the entity . "
				+ "\nSee here - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query");
		
		//System.out.println("Find by manufacture-  named Query " +carRepo.namedQuery1("honda"));
		
		System.out.println("Find by price-  named Query " +carRepo.findByPrice(501));
		int i4 = 350;
		System.out.println("Find all that more then " + i4);
		System.out.println(carRepo.findBBBPrice(i4));
		
		System.out.println("I have problems with defining about manufacture let's check that . ");
		
		System.out.println("U had a syntax problem. U need to write 3 time : select n from Car n where n.foo = ?1 // the  ?1 is must be number. ") ;
		String str1 = "i";

		System.out.println("The next the first iis what ending with, and the second is what ends with. " + str1
				+ "Don't know the differnce of the using in the %.. see inside.");
		System.out.println(carRepo.findAllOwnersEndsWith(str1));
		System.out.println(carRepo.findAllOwnersStartsWith(str1));
		
		System.out.println("The above is written with JPQL syntax. If u want to use native SQL syntax U can change the \n "
				+ "Paramter (=flag) in the @Query. See inside the implementation of that methods.");
		String[] manus = {"mazda","ford"};
		System.out.println("Get with native query the owners of " + manus[0] + " Or " + manus[1] + " \nFirst just from " + manus[0]);
		//System.out.println(carRepo.withNativeSql(manus[0], manus[1]));
		System.out.println(carRepo.withNativeSql(manus[0]));
		System.out.println(carRepo.withNativeSql2(manus[0], manus[1]));
		
		Pageable p1 = PageRequest.of(1, 2);
		int max = 400;
		System.out.println("For using with paging U need to define the count query flag . See the implementation of those two methods ");
		System.out.println(carRepo.pagingWithNative(max, p1).getContent());
		System.out.println("Don't success to do it on JPQL query wiht paging or use the suffix of .count that he is saying about");
		
		System.out.println("Sorting : ");
		List<String> forSort = new ArrayList<String>();
		forSort.add("price");
		//forSort.add("BBB");
		Sort sort1 = new Sort(Sort.Direction.ASC, forSort) ;
		System.out.println("Here is how I use it with sort. This is the good way");
		
		System.out.println(carRepo.findByAndSort("m", sort1));
		
		System.out.println("And that is depracted way : ");
		System.out.println(carRepo.findByAndSort("m", new Sort("manufacture")));
		
		System.out.println("There is something on as Array also. I don't do it right now - U can check it \n "
				+ "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.sorting");

		System.out.println("The ?2 (for example) pars are based on the position in the arguments. ?1 is the first, and so on.\n "
				+ "U can define the names , so u be able to change the order of the defined method vs the query  - without errors. ");
		
		System.out.println(carRepo.withNamedParamsManufactureAndMin(400, "Ford"));
		
		
		
		System.out.println("he is saying that in spring 4 + not need to use @Params . I am trying to check it with other branch\n "
				+ "And in general it's good to practice those section of creation of project and modification . ");
		System.out.println("It's not work - U need to send the Javac flg paramter. An issue to study... ");
		
		System.out.println("SpEL = The Spring Expression Language (SpEL for short)\n "
				+ " is a powerful expression language that supports querying and manipulating an object graph at runtime."
				+ " The language syntax is similar to Unified EL but offers additional features, "
				+ "most notably method invocation and basic string templating functionality.\n see here -  "
				+ "https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/expressions.html");
		int i5 = 300;
		System.out.println("get the car with price less than " + i5 + "\n" + carRepo.withSpEL(i5));
		System.out.println("get the only prices with price less than " + i5 + "\n" + carRepo.tryNative(i5));
		
		System.out.println("get the only prices and owner with price less than " + i5 + "\n" + carRepo.tryNative2(i5));
		System.out.println("As u see in 2 fields it's don't know how to show it. Need to learn it");
		
		System.out.println("It's useful for if u want at the future to change the Entity name U won't need to change all the methods \n "
				+ "For more use case see the refernece(didn't dig there https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions");
		
		
		System.out.println("Updating - ****** https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.modifying-queries");
		System.out.println("***************U NEED TO ANNOTATE THE METHODS WITH @Transaction");
		System.out.println("U can create updatin g");
		System.out.println(carRepo.setFixedFirstnameFor("Good Car", 300));
		
		System.out.println("He talks about not needed in derived methods. But how do I can to update with them - don't know ");
		
		carRepo.deleteByPrice(200);
		carRepo.deleteByPointId(14);
		System.out.println("But it sound that in the @Query method it's more efficient. See the doc. ");
		System.out.println("Great we have finish the studying for now. ");
		int x = 10, y =8 , id =222; String word = "ppp";
		System.out.println("trying to insert new point : \n x = " + x + " | y = " + y + " id = " + id  + " word = " + word);
		pointRepo.makeNewPoint(id, y, x, word);
		
		System.out.println("U can see How I have created new point with that. ");
		System.out.println("And with that, I am finishing this learning. in the future complete it , and do certification on that. "
				+ "See the lesson for understanding things. ");
	}
}
