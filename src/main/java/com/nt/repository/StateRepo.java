package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.State;

public interface StateRepo extends JpaRepository<State, Integer> {
           
	 @Query(nativeQuery = true,value = "SELECT * FROM state_master WHERE ctry_id=:id")
     List<State> getctryid(Integer id);
}
