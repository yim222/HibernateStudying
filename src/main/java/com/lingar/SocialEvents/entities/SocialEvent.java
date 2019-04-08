package com.lingar.SocialEvents.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;





@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@Entity ///@Entity(name = "sss")
public class SocialEvent {
	
	private @Id @GeneratedValue Long socialEventId;
	private String description, moreValue;
	private int fromAge, toAge;
	//String newStr = "I am new ";
	String newStr32 = "I am new ";
	//@ElementCollection(targetClass=EventProperty.class)
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@ManyToOne(cascade=CascadeType.ALL)
	
	//@ManyToOne
	@JoinColumn(name = "eventProperty_id")
	@Embedded
	private EventProperty eventProperty = new EventProperty("something");
	
	@OneToOne(cascade = CascadeType.ALL)//Here it's nested Entity/Object / 
	//It's OneToOne because if not it's need to be some sort of collection. U need to do a casade because if not hibernate won't save it. 
	private EventProperty2 eventProperty2 = new EventProperty2("something entity");
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> myArray; 
	
	//@OneToOne(cascade=CascadeType.ALL)
	//private EventProperties eventProps;
	
	private @Version @JsonIgnore Long version;

	

	private SocialEvent() {}
	
	public SocialEvent(String description, String moreValue, int fromAge, int toAge) {
		super();
		this.description = description;
		this.moreValue = moreValue;
		this.fromAge = fromAge;
		this.toAge = toAge;
		
		
	}


	public SocialEvent( String description, int fromAge, int toAge, String moreValue, List<String> myArray) {
		
		this.description = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.moreValue = moreValue;
		//eventProperty = new EventProperyMutiValues("something");
		
		
		this.myArray = myArray; 
	}
	
	//trying
	public SocialEvent( String description, int fromAge, int toAge, String moreValue) {
		
		this.description = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.moreValue = moreValue;
		
		 List<String> myArray2 = new ArrayList<String>();
		 myArray2.add("no values");
		this.myArray = myArray2; 
	}

	@Override
	public String toString() {
		return "SocialEvent [socialEventId=" + socialEventId + ", description=" + description + ", moreValue="
				+ moreValue + ", fromAge=" + fromAge + ", toAge=" + toAge + ", myArray=" + myArray + ", eventProps="
				 + ", version=" + version + "]";
	}
	
	
}