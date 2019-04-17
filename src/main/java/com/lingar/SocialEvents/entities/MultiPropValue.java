package com.lingar.SocialEvents.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "multi_props_values")
public class MultiPropValue {
	private @Id 
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	Long id;
	@OneToOne //was @oneToOne. Here is the problem ? 
	private MultiPropName MultiPropName;
	
	

	
	String propValue;
	
	public MultiPropValue(){}
	public MultiPropValue(String propName, String propValue) {
		this.MultiPropName = new MultiPropName(propName);
		this.propValue = propValue;
	}
	public MultiPropValue(MultiPropName multiPropName, String propValue) {
		super();
		this.MultiPropName = multiPropName;
		
		this.propValue = propValue;
	}
	
	
	
	
	
}
