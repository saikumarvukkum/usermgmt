package com.nt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CITY_MASTER")
public class City {
    
	@Id
    @GeneratedValue
	private Integer id;
    
    private String name;
    
    
    
}//class
