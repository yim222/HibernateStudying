package com.lingar.SocialEvents.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "single_props_values")
public class SinglePropValue {
	private @Id @GeneratedValue Long eventPropertyId;
	/*
	 * 
	 	cascade = {
	
    CascadeType.MERGE,
    CascadeType.REFRESH
    }
    
     (cascade = CascadeType.ALL)
 */
	@OneToOne (fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	private SinglePropName singlePropName;
	
	

	
	String propValue;
	
	public SinglePropValue(){}
	public SinglePropValue(String propName, String propValue) {
		this.singlePropName = new SinglePropName(propName);
		this.propValue = propValue;
	}
	
	
	
}
