package com.lingar.SocialEvents.tutorial.entities;

import java.util.Random;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Point {
	
	@Id
	@GeneratedValue
	//@Column
	private long id;

	private int xania = new Random().nextInt(4);
	private int yoni = new Random().nextInt(4);
	
	
	public Point(){
		
	}

	
	

}
