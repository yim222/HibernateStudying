package com.lingar.SocialEvents.entities;

import java.util.List;

import javax.persistence.CollectionTable;
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
@Table(name = "multi_props_values")
public class MultiPropValue {
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
	private MultiPropName multiPropName;
	
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	@ElementCollection
	@CollectionTable(name= "multi_props_values_array")
	private List<String> propValue;
	
	public MultiPropValue(){}
	public MultiPropValue(String propName, List<String> propValue) {
		this.multiPropName = new MultiPropName(propName);
		this.propValue = propValue;
	}
	public MultiPropValue(MultiPropName multiPropName, List<String> propValue) {
		super();
		this.multiPropName = multiPropName;
		
		this.propValue = propValue;
	}
	
	
	
	
	
}
