package com.lingar.SocialEvents.tutorial.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Car {
	@Id
	@GeneratedValue
	private UUID id;
	
	private String owner, manufacture;
	private Date year;
	private int price;
	public Car(String owner, String manufacture, Date year, int price) {
		super();
		this.id = id;
		this.owner = owner;
		this.manufacture = manufacture;
		this.year = year;
		this.price = price;
	}
	
	
	
}
