package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.UnlockAccForm;
import com.nt.service.IUserMgmtService;

@RestController
public class UnlockAccRestController {
     @Autowired    
	 private IUserMgmtService service;
     
     @PostMapping("/unlock")
     public ResponseEntity<String> unlockAcc(@RequestBody UnlockAccForm form){
		String status=service.unlockAcc(form);
    	 return new ResponseEntity<>(status,HttpStatus.OK);
    	 
     }
     
}//class
