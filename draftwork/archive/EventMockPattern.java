import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * THis is mock of the creation of the event in the create10Events. It's good after the initial declarations
 * see in its place. 
 * @author lingar
 *
 */
public class EventMockPattern {
	
	//*************MOCK 2***************************//
			singleProps = new ArrayList<>(Arrays.asList(SINGLE_PROPS_VALUES));
			
			
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
			//System.out.println("MULTI_PROPS_VALUES =\n" + MULTI_PROPS_VALUES);
			
			//reset Multiprops to defaults (by removing from the default values )
			passedMultiValues = new TreeMap<String, List<String>>(MULTI_PROPS_VALUES);// = new TreeMap<String, List<String>>(MULTI_PROPS_VALUES);

			for (Map.Entry<String, List<String>> entry : MULTI_PROPS_VALUES.entrySet())
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
			//passedMultiValues.get("jewLvlKeep").remove(1);passedMultiValues.get("jewLvlKeep").remove(2);
			passedMultiValues.get("area").remove(2);passedMultiValues.get("area").remove(1);passedMultiValues.get("area").remove(0);
			//socialEvent = new SocialEvent();
			
			//Set the ages range , time, 
			agesRange = new int[]{30, 44};//from to 
			time = new int[] {20, 30}; //hour, minute
			
			cal.set(2019, 5, 16); // y-m-d
			date = cal.getTime();
			
			
			
			//Write your comment
			createSocialEvent(singleValues, passedMultiValues, "Mock - 5", date, time, agesRange);
			
			//*************End of Event Template******************///
			

}
