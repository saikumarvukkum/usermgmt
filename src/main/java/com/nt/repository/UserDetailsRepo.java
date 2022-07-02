package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.UserDetails;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {
        
	   @Query("SELECT COUNT(*) FROM UserDetails WHERE email=:email AND pwd=:pwd")
	   Integer getEmail(String email,String pwd);
	   
	   @Query("SELECT status FROM UserDetails WHERE email=:email")
	   String getStatus(String email);
	  
	   @Query("SELECT COUNT(*) FROM UserDetails WHERE email=:email" )
	   Integer checkEmail(String email);
	   
	  
	   UserDetails findByEmailAndPwd(String email,String pwd);
	   
	   UserDetails findByEmail(String email);
	 
}//interface
