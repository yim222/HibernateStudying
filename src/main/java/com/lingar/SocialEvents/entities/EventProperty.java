package com.lingar.SocialEvents.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
//@Entity
@Embeddable
@Table(name = "event_properties")
public class EventProperty {
	//private @Id @GeneratedValue Long eventPropertyId;
	
	

	
	String propName;
	
	public EventProperty(){}
	public EventProperty(String propName) {
		
		this.propName = propName;
	}

	
	
}
