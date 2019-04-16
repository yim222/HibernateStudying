package com.lingar.SocialEvents.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
//@Embeddable
@Table(name = "multi_props_lvl1_values")
public class MultiPropLvl1Value {
	private @Id 
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
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
	//@Column(name = "STRINGS", nullable = false)
	//@Column(name = "STRINGS", nullable = false)
	
	@ElementCollection
	//@CollectionTable(name= "multi_props_values_array")
	private List<String> propValue;
	/*
	@Column(name = "STRINGS", nullable = false)
	@ElementCollection
	@CollectionTable(name= "multi_props_values_Set")
	private Set<String> trySet;
	*/
	public MultiPropLvl1Value(){}
	public MultiPropLvl1Value(String propName, List<String> propValue) {
		this.multiPropName = new MultiPropName(propName);
		this.propValue = propValue;
	}
	@Transient
	@ManyToMany( mappedBy="id")
	@ElementCollection
	//@CollectionTable(name= "multi_props_values_array")
	private List<MultiPropLvl2Value> lvl2Values ;
	
	//This is the used constructor
	public MultiPropLvl1Value(MultiPropName multiPropName, List<String> propValue , List<MultiPropLvl2Value> lvl2Values) {
		super();
		this.multiPropName = multiPropName;
		
		//this.propValue = propValue;
		this.lvl2Values = lvl2Values;
		
		
		
		/*
		trySet.add("hi");
		trySet.add("by");
		*/
	}
	
	
	
	
	
}
