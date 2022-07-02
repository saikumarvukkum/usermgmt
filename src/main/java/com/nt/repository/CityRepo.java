package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.City;
import com.nt.model.State;

public interface CityRepo extends JpaRepository<City, Integer> {
        
	 @Query(nativeQuery = true,value = "SELECT * FROM city_master WHERE state_id=:id")
     List<City> getctyid(Integer id);
}
