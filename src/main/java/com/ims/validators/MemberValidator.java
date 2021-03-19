package com.ims.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ims.dto.request.AdminSignupRequestDTO;
import com.ims.entity.Admin;
import com.ims.service.JwtUserDetailsService;

public class MemberValidator implements Validator {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	 @Override
	    public boolean supports(Class<?> aClass) {
	        return Admin.class.equals(aClass);
	    }
	 
	 
	 
	    @Override
	    public void validate(Object o, Errors errors) {
  		 AdminSignupRequestDTO admin = (AdminSignupRequestDTO) o;
		 
		    System.out.println("Getting Email"+ admin.getEmail());
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
//	        
	        
	        if (userDetailsService.findByEmail(admin.getEmail()) != null) {
	            errors.rejectValue("email", "Duplicate.userForm.email");
	        }
	       
	    }
}
