package com.nt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "USR_DTLS")
public class UserDetails {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String pwd;
	
	private Double mobileNo;
	
	private LocalDate dob;
	
	private String gender;
	
	private Integer cityid;
	
	private Integer countryid;
	
	private Integer stateid;
	
	private String status;
	
	@Column(insertable = true, updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(insertable = false, updatable = true)
	@UpdateTimestamp
	private LocalDate UpdatedDate;
	
	

}//class
