package com.lingar.SocialEvents.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "single_props_values")
public class SinglePropValue {
	private @Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	Long id;
	/*
	 * 
	 	cascade = {
	
    CascadeType.MERGE,
    CascadeType.REFRESH
    }
    
     (cascade = CascadeType.ALL)
 */
	@OneToOne //(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	private SinglePropName singlePropName;
	
	

	
	String propValue;
	
	public SinglePropValue(){}
	public SinglePropValue(String propName, String propValue) {
		this.singlePropName = new SinglePropName(propName);
		this.propValue = propValue;
	}
	public SinglePropValue(SinglePropName singlePropName, String propValue) {
		super();
		this.singlePropName = singlePropName;
		
		this.propValue = propValue;
	}
	
	
	
	
	
}
