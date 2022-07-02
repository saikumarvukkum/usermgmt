package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
         
	
	     private String firstName;
         private String lastName;
         private String email;
         private String pwd;
         private Double mobileNo;
         private LocalDate dob;
         private String status;
         private String gender;
         private Integer countryid;
         private Integer stateid;
         private Integer cityid;
         
         
       
}//class
