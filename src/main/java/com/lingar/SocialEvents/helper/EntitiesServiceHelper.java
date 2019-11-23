package com.lingar.SocialEvents.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.lingar.SocialEvents.entities.SocialEvent;
import com.lingar.SocialEvents.services.EntitiesService;

public class EntitiesServiceHelper {
	
	
	
	public static void create10SocialEventMock(EntitiesService ent){
		

		
		//////**************MOCK 1 **************************/
		
		//List<String> singleProps = Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES);//-->this generate fixed sized List
		//see here - https://stackoverflow.com/a/7885607/9727918
		List<String> singleProps = new ArrayList<>(Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES));
		
		SocialEvent socialEvent ;
			//	);//not work - due to eclipse bug :
		//List<String> x = Arrays.asList(EntitiesService.MULTI_PROPS_VALUES.toString());//not work - due to eclipse bug :
		//https://stackoverflow.com/questions/41571839/java-8-type-mismatch-cannot-convert-from-listserializable-to-liststring
		//https://www.google.com/search?newwindow=1&ei=Ao7UXNLUBs-OrwSIm5W4CA&q=Arrays.asList+ype+mismatch%3A+cannot+convert+from+List%3CObject%3E+to+List%3CString%3E&oq=Arrays.asList+ype+mismatch%3A+cannot+convert+from+List%3CObject%3E+to+List%3CString%3E&gs_l=psy-ab.3..0i13i30.557857.558216..559117...0.0..0.146.427.0j3......0....1j2..gws-wiz.......0i71j0j0i22i30.uHdNxUmfyZ8
		Map<String, String> singleValues = new TreeMap<>();
		//System.out.println(singleProps);
		//String[] myStringArray = { "A", "B", "C", "D", "E" };
		//List<String> myStringList = Arrays.asList( myStringArray );
		
		System.out.println(singleProps);
		singleValues.put("eventName", "1-Lingar meeting");
		singleValues.put("publisherName", "Lingar");
		singleValues.put("shortDescription", "Bla");
		singleValues.put("longDescription", "BlaBla Bla");
		singleValues.put("eventLink", "gg.com");
		singleValues.put("imgUrl", "img1.jpg");
		singleValues.put("price", "50");
		singleValues.put("address", "Amal 66");

		
		//creating Multiprops (by removing from the default values )
		//Map<String, List<String>> passedMultiValues = EntitiesService.MULTI_PROPS_VALUES;
		//Map<String, List<String>> passedMultiValues ;// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES); - won't work either
		Map<String, List<String>> passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);

		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		//myobjectListB = new HashMap<Integer,myObject>(myobjectListA);

				//EntitiesService.MULTI_PROPS_VALUES;
		/*
		
		multiPropsPreparation.put("eventType",Arrays.asList("games", "meeting", "speedate","vaacation"));
		multiPropsPreparation.put("matchingIdea",Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting"));
		multiPropsPreparation.put("jewLvlKeep",Arrays.asList("shabbat", "noShabbat"));
		multiPropsPreparation.put("area",Arrays.asList("center", "jerusalem", "north", "south"));
		multiPropsPreparation.put("lowSelection",Arrays.asList("NA"));		
		multiPropsPreparation.put("contactDetails",Arrays.asList("NA", "NA", "NA"));
		
		 */
		
		List<Integer> x =Arrays.asList(1,3);
		System.out.println(passedMultiValues.get("eventType").get(2));
		passedMultiValues.get("eventType").remove(2);
		passedMultiValues.get("eventType").remove(1);//U here - it's throw null 
		passedMultiValues.get("matchingIdea").remove(3);passedMultiValues.get("matchingIdea").remove(2);passedMultiValues.get("matchingIdea").remove(1);
		passedMultiValues.get("jewLvlKeep").remove(1);//passedMultiValues.get("jewLvlKeep").remove(2);
		passedMultiValues.get("area").remove(3);passedMultiValues.get("area").remove(2);passedMultiValues.get("area").remove(0);
		//socialEvent = new SocialEvent();
		
		int[] agesRange = {24, 60};
		int[] time = {1	, 30};
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 26);
		Date date = cal.getTime();
		
		
		

		ent.createSocialEvent(singleValues, passedMultiValues, "Mock-1", date, time, agesRange);
		//////**************END OF MOCK 1 **************************/

		//*************MOCK 2***************************//
		singleProps = new ArrayList<>(Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES));
		
		
		singleValues = new TreeMap<>();
		
		//U need to change here the values 
		
		System.out.println(singleProps);
		singleValues.put("eventName", "2-Singles Gaming");
		singleValues.put("publisherName", "Golan");
		singleValues.put("shortDescription", "Bla");
		singleValues.put("longDescription", "BlaBla Bla");
		singleValues.put("eventLink", "gg.com");
		singleValues.put("imgUrl", "img1.jpg");
		singleValues.put("price", "150");
		singleValues.put("address", "Soko 66");

		//System.out.println("EntitiesService.MULTI_PROPS_VALUES =\n" + EntitiesService.MULTI_PROPS_VALUES);
		
		//reset Multiprops to defaults (by removing from the default values )
		passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);

		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		/*
		
		multiPropsPreparation.put("eventType",Arrays.asList("games", "meeting", "speedate","vaacation"));
		multiPropsPreparation.put("matchingIdea",Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting"));
		multiPropsPreparation.put("jewLvlKeep",Arrays.asList("shabbat", "noShabbat"));
		multiPropsPreparation.put("area",Arrays.asList("center", "jerusalem", "north", "south"));
		multiPropsPreparation.put("lowSelection",Arrays.asList("NA"));		
		multiPropsPreparation.put("contactDetails",Arrays.asList("NA", "NA", "NA"));
		
		 */
		
		//Remove the extras - make mixed kinds -- U need to move the greater first other wise it's generate out of bounds because after the removing the size changed
		passedMultiValues.get("eventType").remove(3);passedMultiValues.get("eventType").remove(2);//U here - it's throw null 		
		passedMultiValues.get("matchingIdea").remove(2);//passedMultiValues.get("matchingIdea").remove(2);passedMultiValues.get("matchingIdea").remove(1);
		passedMultiValues.get("jewLvlKeep").remove(1);//passedMultiValues.get("jewLvlKeep").remove(2);
		passedMultiValues.get("area").remove(2);passedMultiValues.get("area").remove(1);passedMultiValues.get("area").remove(0);
		//socialEvent = new SocialEvent();
		
		//Set the ages range , time, 
		agesRange = new int[]{30, 44};//from to 
		time = new int[] {20, 30}; //hour, minute
		
		cal.set(2019, 5, 16); // y-m-d
		date = cal.getTime();
		
		
		
		//Write your comment
		ent.createSocialEvent(singleValues, passedMultiValues, "Mock - 2", date, time, agesRange);
		
		//*************End of Event Template******************///
		
		//*************MOCK 3***************************//
		singleProps = new ArrayList<>(Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES));
		
		
		singleValues = new TreeMap<>();
		
		//U need to change here the values 
		
		System.out.println(singleProps);
		singleValues.put("eventName", "2-Dating Journey");
		singleValues.put("publisherName", "Devora");
		singleValues.put("shortDescription", "Bla");
		singleValues.put("longDescription", "BlaBla Bla");
		singleValues.put("eventLink", "gg.com");
		singleValues.put("imgUrl", "img1.jpg");
		singleValues.put("price", "200");
		singleValues.put("address", "Neverland 66");

		//System.out.println("EntitiesService.MULTI_PROPS_VALUES =\n" + EntitiesService.MULTI_PROPS_VALUES);
		
		//reset Multiprops to defaults (by removing from the default values )
		passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);

		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		/*
		
		multiPropsPreparation.put("eventType",Arrays.asList("games", "meeting", "speedate","vaacation"));
		multiPropsPreparation.put("matchingIdea",Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting"));
		multiPropsPreparation.put("jewLvlKeep",Arrays.asList("shabbat", "noShabbat"));
		multiPropsPreparation.put("area",Arrays.asList("center", "jerusalem", "north", "south"));
		multiPropsPreparation.put("lowSelection",Arrays.asList("NA"));		
		multiPropsPreparation.put("contactDetails",Arrays.asList("NA", "NA", "NA"));
		
		 */
		
		//Remove the extras - make mixed kinds -- U need to move the greater first other wise it's generate out of bounds because after the removing the size changed
		passedMultiValues.get("eventType").remove(3);passedMultiValues.get("eventType").remove(2);passedMultiValues.get("eventType").remove(0);//U here - it's throw null 		;passedMultiValues.get("eventType").remove(0);
		passedMultiValues.get("matchingIdea").remove(2);//passedMultiValues.get("matchingIdea").remove(2);passedMultiValues.get("matchingIdea").remove(1);
		passedMultiValues.get("jewLvlKeep").remove(0);//passedMultiValues.get("jewLvlKeep").remove(2);
		passedMultiValues.get("area").remove(3);passedMultiValues.get("area").remove(1);passedMultiValues.get("area").remove(0);
		//socialEvent = new SocialEvent();
		
		//Set the ages range , time, 
		agesRange = new int[]{20, 35};//from to 
		time = new int[] {20, 30}; //hour, minute
		
		cal.set(2020, 3, 26); // y-m-d
		date = cal.getTime();
		
		
		
		//Write your comment
		ent.createSocialEvent(singleValues, passedMultiValues, "Mock - 3", date, time, agesRange);
		
		//*************End of MOCK 3 ******************///
		//*************MOCK 4***************************//
		singleProps = new ArrayList<>(Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES));
		
		
		singleValues = new TreeMap<>();
		
		//U need to change here the values 
		
		System.out.println(singleProps);
		singleValues.put("eventName", "4-Beach Dating");
		singleValues.put("publisherName", "Amir");
		singleValues.put("shortDescription", "Bla");
		singleValues.put("longDescription", "BlaBla Bla");
		singleValues.put("eventLink", "gg.com");
		singleValues.put("imgUrl", "img1.jpg");
		singleValues.put("price", "25");
		singleValues.put("address", "Hezel 66");

		//System.out.println("EntitiesService.MULTI_PROPS_VALUES =\n" + EntitiesService.MULTI_PROPS_VALUES);
		
		//reset Multiprops to defaults (by removing from the default values )
		passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);

		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		/*
		
		multiPropsPreparation.put("eventType",Arrays.asList("games", "meeting", "speedate","vaacation"));
		multiPropsPreparation.put("matchingIdea",Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting"));
		multiPropsPreparation.put("jewLvlKeep",Arrays.asList("shabbat", "noShabbat"));
		multiPropsPreparation.put("area",Arrays.asList("center", "jerusalem", "north", "south"));
		multiPropsPreparation.put("lowSelection",Arrays.asList("NA"));		
		multiPropsPreparation.put("contactDetails",Arrays.asList("NA", "NA", "NA"));
		
		 */
		
		//Remove the extras - make mixed kinds -- U need to move the greater first other wise it's generate out of bounds because after the removing the size changed
		passedMultiValues.get("eventType").remove(3);passedMultiValues.get("eventType").remove(1);passedMultiValues.get("eventType").remove(0);//U here - it's throw null 		
		passedMultiValues.get("matchingIdea").remove(2);//passedMultiValues.get("matchingIdea").remove(2);passedMultiValues.get("matchingIdea").remove(1);
		passedMultiValues.get("jewLvlKeep").remove(1);//passedMultiValues.get("jewLvlKeep").remove(2);
		passedMultiValues.get("area").remove(3);passedMultiValues.get("area").remove(2);passedMultiValues.get("area").remove(1);
		//socialEvent = new SocialEvent();
		
		//Set the ages range , time, 
		agesRange = new int[]{40, 64};//from to 
		time = new int[] {20, 30}; //hour, minute
		
		cal.set(2019, 6, 26); // y-m-d
		date = cal.getTime();
		
		
		
		//Write your comment
		ent.createSocialEvent(singleValues, passedMultiValues, "Mock - 4", date, time, agesRange);
		
		//*************End of MOCK 4******************///
		//*************MOCK 5***************************//
		singleProps = new ArrayList<>(Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES));
		
		
		singleValues = new TreeMap<>();
		
		//U need to change here the values 
		
		System.out.println(singleProps);
		singleValues.put("eventName", "5-Party");
		singleValues.put("publisherName", "sarit");
		singleValues.put("shortDescription", "Bla");
		singleValues.put("longDescription", "BlaBla Bla");
		singleValues.put("eventLink", "gg.com");
		singleValues.put("imgUrl", "img1.jpg");
		singleValues.put("price", "35");
		singleValues.put("address", "nizana 66");

		//System.out.println("EntitiesService.MULTI_PROPS_VALUES =\n" + EntitiesService.MULTI_PROPS_VALUES);
		
		//reset Multiprops to defaults (by removing from the default values )
		passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);

		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		
		
		
		/*
		
		multiPropsPreparation.put("eventType",Arrays.asList("games", "meeting", "speedate","vaacation"));
		multiPropsPreparation.put("matchingIdea",Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting"));
		multiPropsPreparation.put("jewLvlKeep",Arrays.asList("shabbat", "noShabbat"));
		multiPropsPreparation.put("area",Arrays.asList("center", "jerusalem", "north", "south"));
		multiPropsPreparation.put("lowSelection",Arrays.asList("NA"));		
		multiPropsPreparation.put("contactDetails",Arrays.asList("NA", "NA", "NA"));
		
		 */
		
		//Remove the extras - make mixed kinds -- U need to move the greater first other wise it's generate out of bounds because after the removing the size changed
		passedMultiValues.get("eventType").remove(2);passedMultiValues.get("eventType").remove(1);passedMultiValues.get("eventType").remove(0);//passedMultiValues.get("eventType").remove(2);//U here - it's throw null 		
		passedMultiValues.get("matchingIdea").remove(2);//passedMultiValues.get("matchingIdea").remove(2);passedMultiValues.get("matchingIdea").remove(1);
		//passedMultiValues.get("jewLvlKeep").remove(1);passedMultiValues.get("jewLvlKeep").remove(2);
		passedMultiValues.get("area").remove(2);passedMultiValues.get("area").remove(1);passedMultiValues.get("area").remove(0);
		//socialEvent = new SocialEvent();
		
		//Set the ages range , time, 
		agesRange = new int[]{19, 28};//from to 
		time = new int[] {20, 30}; //hour, minute
		
		cal.set(2020, 6, 01); // y-m-d
		date = cal.getTime();
		
		
		
		//Write your comment
		ent.createSocialEvent(singleValues, passedMultiValues, "Mock - 5", date, time, agesRange);
		
		//*************End of Event Template******************///
		
		
		//*************MOCK 6***************************//
		singleProps = new ArrayList<>(Arrays.asList(EntitiesService.SINGLE_PROPS_VALUES));
		
		
		singleValues = new TreeMap<>();
		
		//U need to change here the values 
		
		System.out.println(singleProps);
		singleValues.put("eventName", "6-Meyzam ");
		singleValues.put("publisherName", "Ehud");
		singleValues.put("shortDescription", "Bla");
		singleValues.put("longDescription", "BlaBla Bla");
		singleValues.put("eventLink", "gg.com");
		singleValues.put("imgUrl", "img1.jpg");
		singleValues.put("price", "free");
		singleValues.put("address", "Savion 66");

		//System.out.println("EntitiesService.MULTI_PROPS_VALUES =\n" + EntitiesService.MULTI_PROPS_VALUES);
		
		//reset Multiprops to defaults (by removing from the default values )
		passedMultiValues = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(EntitiesService.MULTI_PROPS_VALUES);

		for (Map.Entry<String, List<String>> entry : EntitiesService.MULTI_PROPS_VALUES.entrySet())
	    {
			passedMultiValues.put(entry.getKey(),
	           // Or whatever List implementation you'd like here.
	           new ArrayList<String>(entry.getValue()));
	    }
		
/*
		
		multiPropsPreparation.put("eventType",Arrays.asList("games", "meeting", "speedate","vaacation"));
		multiPropsPreparation.put("matchingIdea",Arrays.asList("spontanic", "connector", "detailsRecording","personalMeeting"));
		multiPropsPreparation.put("jewLvlKeep",Arrays.asList("shabbat", "noShabbat"));
		multiPropsPreparation.put("area",Arrays.asList("center", "jerusalem", "north", "south"));
		multiPropsPreparation.put("lowSelection",Arrays.asList("NA"));		
		multiPropsPreparation.put("contactDetails",Arrays.asList("NA", "NA", "NA"));
		
		 */
		
		//Remove the extras - make mixed kinds -- U need to move the greater first other wise it's generate out of bounds because after the removing the size changed
		passedMultiValues.get("eventType").remove(3);passedMultiValues.get("eventType").remove(2);passedMultiValues.get("eventType").remove(1);//passedMultiValues.get("eventType").remove(2);//U here - it's throw null 		
		passedMultiValues.get("matchingIdea").remove(2);//passedMultiValues.get("matchingIdea").remove(2);passedMultiValues.get("matchingIdea").remove(1);
		//passedMultiValues.get("jewLvlKeep").remove(1);passedMultiValues.get("jewLvlKeep").remove(2);
		passedMultiValues.get("area").remove(2);passedMultiValues.get("area").remove(1);passedMultiValues.get("area").remove(0);
		//socialEvent = new SocialEvent();
		
		//Set the ages range , time, 
		agesRange = new int[]{19, 28};//from to 
		time = new int[] {50, 70}; //hour, minute
		
		cal.set(2019, 9, 21); // y-m-d
		date = cal.getTime();
		
		
		
		//Write your comment
		ent.createSocialEvent(singleValues, passedMultiValues, "Mock - 6", date, time, agesRange);
		
		//*************End of Event Template******************///
		
		
	}
	
	
	public static void displayShortsEvents(EntitiesService ent, List<SocialEvent> list){
		
		for( SocialEvent sEve : list){
			
			System.out.println("ID = " +sEve.getId() + " , comment --> " + sEve.getLingarComment());
			System.out.println("Event-name : " );
			//ent.
			
			
		}
		
		
	}
	
	
}
