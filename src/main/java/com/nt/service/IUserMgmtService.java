package com.nt.service;

import java.util.Map;

import com.nt.bindings.LoginForm;
import com.nt.bindings.UnlockAccForm;
import com.nt.bindings.UserForm;

public interface IUserMgmtService {
	
	    // login screen related methods
		public String loginCheck(LoginForm loginForm);

		// Registration screen related methods
		public String emailCheck(String emailId);

		public Map<Integer, String> loadCountries();

		public Map<Integer, String> loadStates(Integer countryId);

		public Map<Integer, String> loadCities(Integer stateId);
		
		public String saveUser(UserForm userForm);

		// unlock account screen related methods
		public String unlockAcc(UnlockAccForm unlockAccForm);

		// forgot pwd screen related methods
		public String forgotPwd(String emailId);
}//class
