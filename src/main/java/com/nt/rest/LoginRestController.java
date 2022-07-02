package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.LoginForm;
import com.nt.bindings.UserForm;
import com.nt.service.IUserMgmtService;

@RestController
public class LoginRestController {
     @Autowired    
	 private IUserMgmtService service;
     
     @PostMapping("/login")
     public ResponseEntity<String> login(@RequestBody LoginForm form){
    	String status= service.loginCheck(form);
		return new ResponseEntity<String>(status, HttpStatus.OK);
    	 
     }
}//class
