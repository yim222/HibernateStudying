package com.lingar.SocialEventsTrying1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.lingar.SocialEvents.entities.MultiPropValue;

public class StudyingDraftConclusions {

	public static void main(String[] args) {
		
		System.out.println("Some issues I learn while the work : "); 
		System.out.println("************Set and Lists order by insertion .... ");
		
		Set<String> set1 = new HashSet<>();
		set1.add("first");
		set1.add("Second");
		set1.add("third");
		System.out.println(set1 + "\nHash set have no order of insertion or something else");
		Set<String> set2 = new TreeSet<>();
		set2.add("first");
		set2.add("Second");
		set2.add("third");
		
		System.out.println(set2 + "\nTree set also  have no order of insertion or something else");
		
		List <String > list1 = new ArrayList<>();
		list1.add("first");
		list1.add("Second");
		list1.add("third");
		System.out.println(list1 + "\nArray list have  order of insertion");
		
		
		System.out.println("************Array to list  and creating list from array in convenient way .... ");
		List<String> a = Arrays.asList("p");
		
		
		/*
		 * 
		 * Here u can see how I parse the properties into string. 
		 */
		/*
		//List<Integer> idList = students.stream().map(Student::getId).collect(Collectors.toList());
		List<String > properties  = areas.stream().map(MultiPropValue::getPropValue).collect(Collectors.toList());
		//List<String> strProps = properties.stream().map(MultiPropName::getMultiName).collect(Collectors.toList());
		System.out.println(properties);
		*/

	}
}
