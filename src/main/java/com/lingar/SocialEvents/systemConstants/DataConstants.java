package com.lingar.SocialEvents.systemConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/*
 * TODO in the future - Should be with Enums of strings (but U had constraints I think so check it ) 
 */
public class DataConstants {
	//List of the contant properties
	public static final String[] SINGLE_PROPS_VALUES = {
			"eventName",
			"isOrganizer",
			"publisherName",
			"address",
			"shortDescription",
			"longDescription",
			"eventLink",
			"imgUrl",
			"price"

	};
	
	//do it as Treemap with ArrayList 
	/**
	 	
		
		eventType
		area
		matchingIdea
		jewLvlKeep
		contactDetails
		lowSelection

	 */
	public static final Map <String, List<String>> multiPropsPreparation = new TreeMap<>();
	//new ArrayList<>(Arrays.asList
	//new ArrayList<>(new ArrayList<>(Arrays.asList
	
	static{//Need to change in the next . - here I initialize the Data to pass to the constant. 
		multiPropsPreparation.put("eventType",new ArrayList<>(Arrays.asList("games", "meeting", "speedate","vaacation")));
		multiPropsPreparation.put("matchingIdea",new ArrayList<>(Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting")));
		multiPropsPreparation.put("jewLvlKeep",new ArrayList<>(Arrays.asList("shabbat", "noShabbat")));
		multiPropsPreparation.put("area",new ArrayList<>(Arrays.asList("center", "jerusalem", "north", "south")));
		multiPropsPreparation.put("lowSelection",new ArrayList<>(Arrays.asList("NA")));		
		multiPropsPreparation.put("contactDetails",new ArrayList<>(Arrays.asList("NA-name", "NA-Tel", "NA-Mail")));// U need unique initial values, otherwise - it will generate error of not unique
		
		 
   }
	List<String> a = Arrays.asList("p");
	//List<String> a = new ArrayList<>(Arrays.asList("first","second","third");
	public static final Map <String, List<String>> MULTI_PROPS_VALUES = multiPropsPreparation;
	
	
}
	
