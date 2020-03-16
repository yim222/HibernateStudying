package com.lingar.SocialEvents.dataObjects;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.lang.NonNull;//Supported also 
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor//Must all Args this allow in the WS to send others signature (I think that it's connected to something else) but not on the main .
public class TestData {
	@NonNull
	private String 
	
	x, 
	
	y;
	@NonNull
	private String z ;
	
	@NonNull
	Map <String, List<String>> myvalues;
	
	String regular;
	

	public static void main(String[] args) {
		TestData t = new TestData("hi", "bye", "hh", null, "sd");
		
		System.out.println("t = " + t);
		
		
	}
}
