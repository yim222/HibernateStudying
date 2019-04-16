package com.lingar.SocialEvents.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "multi_props_lvl1_values")
public class MultiPropLvl2Value {
	private @Id 
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	Long id;
	
	//@OneToOne //(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	//private MultiPropName multiPropName;
	
	private String value;
	
	
	public MultiPropLvl2Value(){}
	public MultiPropLvl2Value(String value) {
		this.value = value;
	}
	
	
	
	
	
	
	
	
}
