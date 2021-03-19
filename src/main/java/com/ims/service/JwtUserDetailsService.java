package com.ims.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ims.configs.WebSecurityConfig;
import com.ims.core.Mapper;
import com.ims.dto.request.AdminSignupRequestDTO;
import com.ims.dto.request.UpdatePasswordRequestDTO;
import com.ims.entity.Admin;
import com.ims.repository.AdminRepository;

@Service
public class JwtUserDetailsService  implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private WebSecurityConfig webSecurityConfig;
	
	private Mapper dtoUtil = new Mapper();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = this.adminRepository.findByEmail(username);
		if(admin == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), new ArrayList<>());
	}
	
	
	public boolean save(AdminSignupRequestDTO adminSignup) {
	       try {
	    	   boolean feedback = false;
	    	   int count = this.adminRepository.countAccounts();
	    	   if(count > 0) {
	    		  return feedback;
	    	   }
	    	   Admin addAdmin = (Admin) this.dtoUtil.convertToEntity(new Admin(), adminSignup);
	    	   addAdmin.setPassword(webSecurityConfig.passwordEncoder().encode(adminSignup.getPassword()));
	    	   this.adminRepository.save(addAdmin);
	    	   return feedback = true;
	       }	
	       catch(Exception ex) {
	    	   throw ex;
	       } 
		}
		
	  
		public Admin findByEmail(String email) {
			   return this.adminRepository.findByEmail(email);
		}
		
		public boolean updateNewPassword(UpdatePasswordRequestDTO updatePassword) {
			   try {
				   boolean feedback = false;
				   BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(); 
				   Admin admin = this.adminRepository.findAll().get(0);
				   if(!admin.equals(null)) {
					   if(bcrypt.matches(updatePassword.getOldPassword(), admin.getPassword())) {
						   admin.setPassword(bcrypt.encode(updatePassword.getNewPassword()));
						   this.adminRepository.save(admin);
						   feedback = true;
					   }
				   }
				   return feedback;
			   }
			   catch(Exception ex) {
				   throw ex;
			   }
		}
		
		

}
