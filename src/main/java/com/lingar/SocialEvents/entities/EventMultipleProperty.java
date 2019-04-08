package com.lingar.SocialEvents.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OrderColumn;

import lombok.Data;

@Data
@Entity
public class EventMultipleProperty extends EventProperty2{
	
	
	String specialValue = "I am child in EventMultipleProperty";
	//@ElementCollection(fetch = FetchType.EAGER)
	@OrderColumn
	String[] specialValue2 = {"I am child in the array " , "I am 2 "};
	@ElementCollection
	private List<String> propsList = new ArrayList<String>();
	public EventMultipleProperty(String s){
		super (s);
		propsList.addAll(Arrays.asList(
				"a", "b"
				
				));
	}
	
}
