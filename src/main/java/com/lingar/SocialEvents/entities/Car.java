package com.lingar.SocialEvents.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "cars_names")
public class Car {
	private @Id @GeneratedValue Long eventPropertyId;
	
	

	
	String name;
	
	//@OneToOne(targetEntity = String.class)
	String value;
	
	public Car(){}
	public Car(String name, String value) {
		
		this.name = name;
		this.value = value;
	}

	
	
}
