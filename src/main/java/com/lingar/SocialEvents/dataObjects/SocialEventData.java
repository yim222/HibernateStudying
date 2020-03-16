package com.lingar.SocialEvents.dataObjects;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


/**
 * Object for contain the data that Social event needs. 
 * @author lingar
 * 
 * After creating properly you can use it with 
 * EntitiesService.
 * public void createSocialEvent(
 * 			Map<String, String> singleValues,
			Map<String, List<String>> multiValues,
			String comment, 
			Date date, 
			int[] time,
			int[] agesRange){...}
 *
 */
@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@NoArgsConstructor
@RequiredArgsConstructor
public class SocialEventData {
	
	@NonNull
	private Map<String, String> singleValues;
	
	
	private Map<String, List<String>> multiValues;
	
	private String comment = "no comment";
	
	private Date date;
	
	private int[] time;
	
	private int[] agesRange;

}
