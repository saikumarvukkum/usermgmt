package com.nt.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.UserForm;
import com.nt.service.IUserMgmtService;

@RestController
@CrossOrigin
public class RegistrationRestController {
     @Autowired    
	 private IUserMgmtService service;
     
     @GetMapping("/emailcheck/{emailid}")
     public ResponseEntity<String> login(@PathVariable String emailid){
    	String status= service.emailCheck(emailid);
		return new ResponseEntity<String>(status, HttpStatus.OK);
    	 
     }
     
     @GetMapping("/countries")
     public ResponseEntity<Map<Integer,String>> countries(){
    	Map<Integer,String> map= service.loadCountries();
		return new ResponseEntity<>(map, HttpStatus.OK);
    }
     
     @GetMapping("/state/{statid}")
     public ResponseEntity<Map<Integer,String>> states(@PathVariable Integer statid){
    	Map<Integer,String> map= service.loadStates(statid);
		return new ResponseEntity<>(map, HttpStatus.OK);
    }
     
     @GetMapping("/city/{ctyid}")
     public ResponseEntity<Map<Integer,String>> cities(@PathVariable Integer ctyid){
    	Map<Integer,String> map= service.loadCities(ctyid);
		return new ResponseEntity<>(map, HttpStatus.OK);
    }
     
     @PostMapping("/user")
     public ResponseEntity<String> saveUser(@RequestBody UserForm form){
    	String status= service.saveUser(form);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
     
     
}//class
