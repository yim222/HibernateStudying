package com.lingar.SocialEvents.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.entities.MultiPropValue;
import com.lingar.SocialEvents.entities.SinglePropValue;
import com.lingar.SocialEvents.entities.SocialEvent;

public interface SocialEventRepositoryBeforeClean extends PagingAndSortingRepository<SocialEvent, Long>{
	
	//find by on of multi props values
	//multiPropsValuesSet
	List<SocialEvent> findByMultiPropsValuesSetIn(MultiPropValue m);
	List<SocialEvent> findByMultiPropsValuesSetIn(Set<MultiPropValue> m);
	
	//select b.fname, b.lname from Users b JOIN b.groups c where c.groupName = :groupName 
	//@Query("select u from Car u where u.owner like %?1")//if the %before the par it's take the end. and if before check if stating with. Why ? 
  	//List<Car> findAllOwnersEndsWith(String w);
	@Query("select a from SocialEvent a JOIN a.singlePropsValuesList b where b.singlePropName.propName = ?1")//See how u access the inner field.
	//Not like in derived with capitalize but with dot. i.k
	List<SinglePropValue>tryQuery(String propName);
	
	@Query("select a.singlePropsValuesList from SocialEvent a JOIN a.singlePropsValuesList b where b.singlePropName.propName = ?1 and  a.id = ?2")//See how u access the inner field.
	//Not like in derived with capitalize but with dot. i.k
	//See how in the select x - the x is define what the returned values could be value. 
	List<SinglePropValue>tryQuery2(String propName, long  id);
	
	//@Query("select a.singlePropsValuesList.SinglePropValue from SocialEvent a JOIN a.singlePropsValuesList b where b.singlePropName.propName = ?1 and  a.id = ?2")//See how u access the inner field.
	//Not like in derived with capitalize but with dot. i.k
	//See how in the select x - the x is define what the returned values could be value. 
	//SinglePropValue tryQuery3(String propName, long  id);
	
	
	/**
	 * The query. 
I want to find all the events that – 
1- have one of – or that or that (args... eventType – stringg  )
AND
2- - have one of – or that or that (args... jewLvlKeep – stringg  )
And
3- - have one of – or that or that (args... area – stringg  )
AND
4- Between AgesInclude (int from int to ) 
AND 
5- BetweenDatesInclude (Date from , Date to ) . 

	 */
	/*
	//select item from Item item where item.name IN :names
	@Query("select event from SocialEvent event WHERE "
			+ "event.multiPropsValuesSet IN ?1")
	List<SocialEvent> filterOne(Set<MultiPropValue> eventTypes);
	
	//select item from Item item where item.name IN :names
		@Query(value = "select event from SocialEvent event WHERE "
				+ "event.multiPropsValuesSet IN ?1", nativeQuery = true)
		List<SocialEvent> filterTwo(MultiPropValue eventTypes);
	*/
	@Query("select event from SocialEvent event WHERE "
			+ "event.multiPropsValuesSet IN ?1")//Join missing ?
	List<SocialEvent> filterTwo(MultiPropValue eventTypes);
	
	@Query("select event from SocialEvent event JOIN event.multiPropsValuesSet multiPropVal  WHERE "
			+ "multiPropVal IN ?1")//Join missing ?
	List<SocialEvent> filterTwoFixed(MultiPropValue eventTypes);
	
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT e from SocialEvent e join e.multiPropsValuesSet m where m = ?1")//--- work 
	List<SocialEvent> filter3(MultiPropValue eventTypes);
	
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT e from SocialEvent e join e.multiPropsValuesSet m where m in ?1")//--- work 
	List<SocialEvent> filter4(List <MultiPropValue> eventTypes);
	
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT DISTINCT e from SocialEvent e join e.multiPropsValuesSet m where m in ?1")//--- work 
	List<SocialEvent> filter5(List <MultiPropValue> eventTypes);
	

	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT DISTINCT e from SocialEvent e join e.multiPropsValuesSet m where( m in ?1"
			+ " AND   m in ?2)")//--- work 
	List<SocialEvent> filter6(List <MultiPropValue> eventTypes, List <MultiPropValue> areas);
		
	/*
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT DISTINCT e from SocialEvent e join e.multiPropsValuesSet m where( m in ?1"
			+ " AND join e.multiPropsValuesSet n  n in ?2)")//--- work 
	List<SocialEvent> filter7(List <MultiPropValue> eventTypes, List <MultiPropValue> areas);
			*/
	
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT DISTINCT e from SocialEvent e join e.multiPropsValuesSet m join e.multiPropsValuesSet c where( m in ?1"
			+ " AND   c in ?2)")//--- work 
	List<SocialEvent> filter8(List <MultiPropValue> eventTypes, List <MultiPropValue> areas);
	
	//Filter with the three parameters 
	@Query("SELECT DISTINCT e from SocialEvent e "
			+ "join e.multiPropsValuesSet m "
			+ "join e.multiPropsValuesSet c "
			+ "join e.multiPropsValuesSet a   "
			+ "WHERE ("
			+ "m in ?1"
			+ " AND   c in ?2 "//" AND   c in ?2"//--- not work beause U need to left spacec 
			+ "AND a in ?3)")//
	List<SocialEvent> filter9(List <MultiPropValue> eventTypes, List <MultiPropValue> areas, List <MultiPropValue> jewLvlKeep);
		
	
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT DISTINCT e from SocialEvent e "
			+ "join e.multiPropsValuesSet m "
			+ "join e.multiPropsValuesSet c "
			+ "join e.multiPropsValuesSet a "
			+ "where( "
			+ "m in ?1"
			+ " AND   c in ?2 "
			+ "AND a in ?3)")//--- Yeah - works 
	List<SocialEvent> filter10(List <MultiPropValue> eventTypes, List <MultiPropValue> areas, List <MultiPropValue> jewLvlKeep);
		
	
	//@Query("select p from Person p left join p.qualifications q where q.experienceInMonths > ?1 and q.experienceInMonths < ?2 and q.name = ?3")
	@Query("SELECT DISTINCT e from SocialEvent e join e.multiPropsValuesSet m join e.multiPropsValuesSet c join e.multiPropsValuesSet a where( m in ?1"
			+ " AND   c in ?2 AND a in ?3)")//--- work 
	List<SocialEvent> filter11(List <MultiPropValue> eventTypes, List <MultiPropValue> areas, List <MultiPropValue> jewLvlKeep);
	

	//Filter with three parameters + between ages 
	@Query("SELECT DISTINCT e from SocialEvent e "
			+ "join e.multiPropsValuesSet m "
			+ "join e.multiPropsValuesSet c "
			+ "join e.multiPropsValuesSet a "
			+ "where(( "
			+ "m in ?1"
			+ " AND   c in ?2 "
			+ "AND a in ?3) "
			+ "AND "
			+ "(e.fromAge between 15 and 16))")//--- work ? 
	List<SocialEvent> filter12(List <MultiPropValue> eventTypes, List <MultiPropValue> areas, List <MultiPropValue> jewLvlKeep);
	
	//Filter with simple between
	@Query("SELECT DISTINCT e from SocialEvent e "
			
			+ "WHERE e.fromAge BETWEEN ?1 AND ?2")//--- work ? 
	List<SocialEvent> filter13(int from , int to);
	
	//Filter with checking overlap range - real need - ? WORK ? 
	@Query("SELECT DISTINCT e from SocialEvent e "
			
			+ "WHERE (e.fromAge BETWEEN ?1 AND ?2) "
			+ "OR (e.toAge BETWEEN ?1 AND ?2)")//--- work ? 
	List<SocialEvent> filter14(int from , int to);
	
	//Filter with checking overlap range - real need - ? WORK ? - fixed think that yeah
	@Query("SELECT DISTINCT e from SocialEvent e "
			
			+ "WHERE (e.fromAge BETWEEN ?1 AND ?2) "
			+ "OR (e.toAge BETWEEN ?1 AND ?2 ) "
			+ "OR (?1 BETWEEN e.fromAge AND e.toAge )")//--- work ? 
	List<SocialEvent> filter141(int from , int to);
	//Filter with date between - works - it include, but checking the time also . 
	//U need to make sure that the pars have sent in the start or end of the day, and the creating of the time is at the middle of the day. 
	
	@Query("SELECT DISTINCT e from SocialEvent e "
			
			+ "WHERE (e.date BETWEEN ?1 AND ?2) ")
			
	List<SocialEvent> filter15(Date from , Date to); //WORKS !(SHOULD WORK) 
	
	//Here I will mix the things by all the filters. 
	//Filter with three parameters + between ages 
	@Query("SELECT DISTINCT e from SocialEvent e "
			+ "join e.multiPropsValuesSet m "
			+ "join e.multiPropsValuesSet c "
			+ "join e.multiPropsValuesSet a "
			+ "WHERE ( "
			+ "m in ?1"
			+ " AND   c in ?2 "
			+ "AND a in ?3) "
			+ "AND "
			+ "((e.fromAge BETWEEN ?4 AND ?5) "
			+ "OR (e.toAge BETWEEN ?4 AND ?5)"
			+ "OR (?4 BETWEEN e.fromAge AND e.toAge )) "
			+ "AND (e.date BETWEEN ?6 AND ?7) "
			+ "ORDER BY e.date ASC")//--- work ? 
	List<SocialEvent> filter16(
			List <MultiPropValue> eventTypes,
			List <MultiPropValue> areas, List <MultiPropValue> jewLvlKeep,
			int fromAge, int toAge,
			Date from , Date to);
	
	//Find all greater than or equal for today --- >> U need to pass the hours reset for not lose the events that have earlier hour. 
	List<SocialEvent> findByDateGreaterThanEqualOrderByDateAsc(Date date);
	
	
	
}
























