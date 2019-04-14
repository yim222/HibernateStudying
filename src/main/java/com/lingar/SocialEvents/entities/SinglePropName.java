package com.lingar.SocialEvents.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "single_props_names")
public class SinglePropName {
	private @Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	Long id;
	
	

	@Column(length = 32, unique = true)//U must define length for set it Unique
	String propName;
	
	public SinglePropName(){}
	public SinglePropName(String propName) {
		
		this.propName = propName;
	}

	
	
}
