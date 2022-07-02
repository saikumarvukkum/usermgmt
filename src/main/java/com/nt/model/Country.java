package com.nt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class Country {
    
	@Id
    @GeneratedValue
	private Integer id;
    
    private String name;
  
   @OneToMany(fetch = FetchType.EAGER)
   @JoinColumn(name = "CTRY_ID")
   private List<State> state;
    
    
    
}//class
