package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.Country;
import com.nt.model.State;

public interface CountryRepo extends JpaRepository<Country, Integer> {
     
	 
}
