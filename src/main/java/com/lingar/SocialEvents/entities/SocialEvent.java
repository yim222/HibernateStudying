package com.lingar.SocialEvents.entities;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	
	
	@Id
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate - I remove it since affraid its make problem (not sure But don't have time to check)
	private Long id;
	
	//For my testing comment
	private String lingarComment = "No Comment";
	private int fromAge = 0, toAge = 120;
		
	//List of single props values
	@ElementCollection
	@CollectionTable(name="social_events_single_props_values")
	private List<SinglePropValue> singlePropsValuesList = new ArrayList<>();
	
	//List(set) of Multi prop Values
	@ManyToMany//trying to solve the problem 
	@CollectionTable(name="social_events_multi_values")
	private Set<MultiPropValue> lingarValuesList = new HashSet<MultiPropValue>();
		
	private @Version @JsonIgnore Long version;

	 

	public SocialEvent() {}
	
	public SocialEvent(String description,int fromAge, int toAge, List<SinglePropValue> singlePropsValuesList
			) {
		super();
		this.lingarComment = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.singlePropsValuesList = singlePropsValuesList;
		

		
		
	}

	
	
	
}