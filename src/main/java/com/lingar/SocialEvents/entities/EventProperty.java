package com.lingar.SocialEvents.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EventProperty {
	private @Id @GeneratedValue Long eventPropertyId;
	
	

	//parent
	@Column(name = "prop_name")
	String name;
	
	public EventProperty(){}
	public EventProperty(String name) {
		
		this.name = name;
	}

	
	
}
