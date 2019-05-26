package com.lingar.SocialEventsTrying1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Draft {

	public static void main(String[] args) {
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


	}
}
