package com.nt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.LoginForm;
import com.nt.bindings.UnlockAccForm;
import com.nt.bindings.UserForm;
import com.nt.model.City;
import com.nt.model.Country;
import com.nt.model.State;
import com.nt.model.UserDetails;
import com.nt.repository.CityRepo;
import com.nt.repository.CountryRepo;
import com.nt.repository.StateRepo;
import com.nt.repository.UserDetailsRepo;
import com.nt.util.EmailUtil;

@Service
public class UserMgmtServiceimpl implements IUserMgmtService {
	
	@Autowired
	private UserDetailsRepo userRepo;
	
	@Autowired
	private CityRepo ctyRepo;
	
	@Autowired
	private CountryRepo ctryRepo;
	
	@Autowired
	private StateRepo stateRepo;
     
	@Autowired
	private EmailUtil email;

	@Override
	public String loginCheck(LoginForm loginForm) {
		
		Integer id=userRepo.getEmail(loginForm.getUname(), loginForm.getPwd());
		String status=userRepo.getStatus(loginForm.getUname());
		
	/*	if(id==1 && status=="Active") {
			
			return "Email And Password is Correct And Active";
		}
		else {
			return "Email And Password Does Not Exists";
			
		}
		*/
		
		if(id==0) {
			return "Credentials are Not at all Valid";
		}
		
		if(status.equals("LOCKED")) {
			return "Account is Locked";
			
		}
		
		return "Success";
	}

	@Override
	public String emailCheck(String emailId) {
		Integer id=userRepo.checkEmail(emailId);
		
		return (id>1)?"Email Already Available":"Email IS UNIQUE" ;
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<Country> list = ctryRepo.findAll();
		Map<Integer,String> map=new HashMap<Integer, String>();
		list.forEach(lst->{
			map.put(lst.getId(),lst.getName());
		});
		return map;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		List<State> list = stateRepo.getctryid(countryId);
		Map<Integer,String> map=new HashMap<Integer, String>();
		list.forEach(obj->{
			map.put(obj.getId(),obj.getName());
		});
		return map;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<City> list = ctyRepo.getctyid(stateId);
		Map<Integer,String> map=new HashMap<Integer, String>();
		list.forEach(obj->{
			map.put(obj.getId(),obj.getName());
		});
		return map;
	}

	@Override
	public String saveUser(UserForm userForm) {
		
		userForm.setPwd(generateRandomPwd(6));//need to be inserted random pwd
		UserDetails dtls=new UserDetails();
		BeanUtils.copyProperties(userForm, dtls);
		dtls.setStatus("LOCKED");
		UserDetails save = userRepo.save(dtls);
		
		String mailbody=readUnlockEmailBody(userForm);
		email.sendMail(userForm.getEmail(), "User Registration--XYZ IT", mailbody);
		return (save!=null)?"Record Saved":"Record Not Saved";
	}

	@Override
	
	public String unlockAcc(UnlockAccForm unlockAccForm) {
		//Integer id=userRepo.getEmail(unlockAccForm.getEmail(), unlockAccForm.getTmppwd());
		UserDetails details=userRepo.findByEmailAndPwd(unlockAccForm.getEmail(), unlockAccForm.getTmppwd());
		
		if(details==null) {
			return "InValid Temporary Password";
		}
		
		details.setPwd(unlockAccForm.getNewpwd());
		details.setStatus("UNLOCKED");
		userRepo.save(details);
		
		return "SUCCESS";
	}

	@Override
	public String forgotPwd(String emailId) {
		UserDetails dtls= userRepo.findByEmail(emailId);
		if(dtls==null) {
			return "Invalid Email";
		}
		
		String body=recoverPassword(dtls);
		email.sendMail(emailId, "Password Recovery", body);
		return "Password Sent to your registered Email";
	}
	
      private static  String	generateRandomPwd(int n) {
    	  // chose a Character random from this String
          String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                      + "0123456789"
                                      + "abcdefghijklmnopqrstuvxyz";
    
          // create StringBuffer size of AlphaNumericString
          StringBuilder sb = new StringBuilder(n);
    
          for (int i = 0; i < n; i++) {
    
              // generate a random number between
              // 0 to AlphaNumericString variable length
              int index
                  = (int)(AlphaNumericString.length()
                          * Math.random());
    
              // add Character one by one in end of sb
              sb.append(AlphaNumericString
                            .charAt(index));
          }
    
          return sb.toString();
      
      }//method(-)
      
      private String readUnlockEmailBody(UserForm form) {
    	  String body="";
    	  StringBuffer sb=new StringBuffer();
    	  Path filePath=Paths.get("UNLOCK-EMAIL-TEMPLATE.txt");
    	  
    	  try {
			
    		  Stream<String> lines=Files.lines(filePath);
    		  lines.forEach(data->{
    			  sb.append(data);
    		  });
    		  body=sb.toString();
    		  body=body.replace("{FNAME}", form.getFirstName());
    		  body=body.replace("{LNAME}", form.getLastName());
    		  body=body.replace("{TEMP-PWD}", form.getPwd());
    		  body=body.replace("{EMAIL}", form.getEmail());
		} 
    	  catch (IOException e) {
			
			e.printStackTrace();
		}
		return body;
      }//method(-)
      
      private String recoverPassword(UserDetails  dtls) {
    	  String body="";
    	  StringBuffer sb=new StringBuffer();
    	  Path filePath=Paths.get("RECOVER-PASSWORD.txt");
    	  
    	  try {
			
    		  Stream<String> lines=Files.lines(filePath);
    		  lines.forEach(data->{
    			  sb.append(data);
    		  });
    		  body=sb.toString();
    		  body=body.replace("{FNAME}", dtls.getFirstName());
    		  body=body.replace("{LNAME}", dtls.getLastName());
    		  body=body.replace("{PWD}",dtls.getPwd());
		} 
    	  catch (IOException e) {
			
			e.printStackTrace();
		}
		return body;
      }//method(-)
}//class
