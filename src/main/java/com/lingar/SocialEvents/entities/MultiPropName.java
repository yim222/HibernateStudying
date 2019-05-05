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
@Table(name = "multi_props_names")
public class MultiPropName {
	private @Id 
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	Long id;
	
	

	@Column//(length = 32, unique = true)//U must define length for set it Unique - first implement support for unique then do it unique
	private String multiName;
	
	public MultiPropName(){}
	public MultiPropName(String multiName) {
		
		this.multiName = multiName;
	}

	
	
}
