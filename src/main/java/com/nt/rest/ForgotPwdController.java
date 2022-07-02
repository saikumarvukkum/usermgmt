package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.IUserMgmtService;

@RestController
@CrossOrigin
public class ForgotPwdController {
     
	@Autowired    
	 private IUserMgmtService service;
     
     @GetMapping("/pwd/{email}")
     public ResponseEntity<String> forgetpwd(@PathVariable String email){
    	String status= service.forgotPwd(email);
		return new ResponseEntity<String>(status, HttpStatus.OK);
    	 
     }
}//class
