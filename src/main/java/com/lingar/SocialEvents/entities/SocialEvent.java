package com.lingar.SocialEvents.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;





@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@Entity ///@Entity(name = "sss")
@Table(name = "social_events")
public class SocialEvent {
	/*
	//@OrderColumn
	static SinglePropName[] a = {	
			new SinglePropName("name"),
			new SinglePropName("tel")
	};
	*/
	//public static ArrayList<SinglePropName> staticList =	
			
			
	
	@Id
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	private Long id;
	/* Have tried to generate UUID but it's makes probelms. maybe in the future 
	@Id 
	@Column(length = 70)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue
	private String id;	
	*/
	private String description, moreValue;
	private int fromAge, toAge;
	String newStr32 = "I am new ";
	
	
	//@ManyToOne
	@JoinColumn(name = "eventProperty_id")
	@Embedded
	private EventProperty eventProperty = new EventProperty("something");
	
	@OneToOne(cascade = CascadeType.ALL)//Here it's nested Entity/Object / 
	//It's OneToOne because if not it's need to be some sort of collection. U need to do a casade because if not hibernate won't save it. 
	private EventProperty2 eventProperty2 = new EventProperty2("something entity");
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> myArray; 
	
	@OneToOne(cascade = CascadeType.ALL)
	Car car = new Car("honda", "Izhar");
	
	@OneToOne(cascade = CascadeType.ALL)
	private SinglePropValue singlePropValue = null;//new SinglePropValue("name", "lingar event");
	
	//@OneToOne(cascade = CascadeType.ALL)
	//private SinglePropValue singlePropValue2 = new SinglePropValue("name2", "lingar event2");
	//@OneToMany(cascade = CascadeType.ALL)- U can do it with that and not save before. But right now I save before. 
	@ElementCollection
	@CollectionTable(name="social_events_single_props_values")
	private List<SinglePropValue> singlePropsValuesList = new ArrayList<>();
	
	//Trying to add 2 Multi's 
	@ElementCollection
	@CollectionTable(name="social_events_multi_props_values")
	private List<MultiPropLvl1Value> multiPropsValuesList = new ArrayList<>();

	
	private @Version @JsonIgnore Long version;

	 

	public SocialEvent() {}
	
	public SocialEvent(String description, String moreValue, int fromAge, int toAge, List<SinglePropValue> singlePropsValuesList
			) {
		super();
		this.description = description;
		this.moreValue = moreValue;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.singlePropsValuesList = singlePropsValuesList;
		

		
		
	}
	
	//constructor for the multi prop
	public SocialEvent(String description, String moreValue, int fromAge, int toAge, List<SinglePropValue> singlePropsValuesList
			, List<MultiPropLvl1Value> multiPropsValuesList) {
		super();
		this.description = description;
		this.moreValue = moreValue;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.singlePropsValuesList = singlePropsValuesList;
		this.multiPropsValuesList = multiPropsValuesList;

		
		
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
		return "SocialEvent [socialEventId=" + id + ", description=" + description + ", moreValue="
				+ moreValue + ", fromAge=" + fromAge + ", toAge=" + toAge + ", myArray=" + myArray + ", eventProps="
				 + ", version=" + version + "]";
	}
	
	
}