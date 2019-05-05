package com.lingar.SocialEvents.tutorial.entities;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Car {
	@Id
	@GeneratedValue
	@Column(length=16)//U need that for using in UUID - https://stackoverflow.com/questions/48459648/spring-boot-jpa-findbyuuid
	private UUID id;
	
	private String owner, manufacture;
	private Date year;
	private int price;
	private int threeWordsProperty = new Random().nextInt(1000);
	
	@OneToOne(cascade = CascadeType.PERSIST )
	private Point point = new Point();
	
	//This is same field to all cars . 
	private String sameField = "same";
	
	@Column(name = "customName")
	private int otherName = new Random().nextInt(7);

	
	public Car(){
		
	}
	public Car(String owner, String manufacture, Date year, int price) {
		super();
		this.id = id;
		this.owner = owner;
		this.manufacture = manufacture;
		this.year = year;
		this.price = price;
	}
	
	
	
}
