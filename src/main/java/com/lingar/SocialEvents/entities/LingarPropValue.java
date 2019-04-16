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
@Table(name = "lingar_props_values")
public class LingarPropValue {
	private @Id 
	@GeneratedValue//(strategy = GenerationType.IDENTITY)//SO the genration will be in each entity seperate 
	Long id;
	@OneToOne //was @oneToOne. Here is the problem ? 
	private LingarPropName lingarPropName;
	
	

	
	String propValue;
	
	public LingarPropValue(){}
	public LingarPropValue(String propName, String propValue) {
		this.lingarPropName = new LingarPropName(propName);
		this.propValue = propValue;
	}
	public LingarPropValue(LingarPropName lingarPropName, String propValue) {
		super();
		this.lingarPropName = lingarPropName;
		
		this.propValue = propValue;
	}
	
	
	
	
	
}
