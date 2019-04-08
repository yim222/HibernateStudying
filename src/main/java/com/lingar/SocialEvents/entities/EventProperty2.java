package com.lingar.SocialEvents.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "event_properties2")
public class EventProperty2 {
	private @Id @GeneratedValue Long eventPropertyId;
	
	

	
	String propName;
	
	public EventProperty2(){}
	public EventProperty2(String propName) {
		
		this.propName = propName;
	}

	
	
}
