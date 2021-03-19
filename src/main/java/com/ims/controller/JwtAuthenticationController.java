package com.ims.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.configs.JwtTokenUtil;
import com.ims.core.Mapper;
import com.ims.dto.request.AdminSignupRequestDTO;
import com.ims.dto.request.SignInRequestDTO;
import com.ims.dto.request.UpdatePasswordRequestDTO;
import com.ims.dto.response.SigninResponseDTO;
import com.ims.dto.response.UserBasicInfo;
import com.ims.entity.Admin;
import com.ims.service.JwtUserDetailsService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	private Mapper dtoUtil = new Mapper();
	
	@PutMapping(value = "/changePassword")
	public ResponseEntity<?> updateNewPassword(@RequestBody UpdatePasswordRequestDTO updatePassword){
		   try {
			   boolean feedback = this.userDetailsService.updateNewPassword(updatePassword);
			   if(feedback) {
				   return ResponseEntity.ok("OK");
			   }
			       return ResponseEntity.ok(null);
		   }
		   catch(Exception ex) {
			   throw ex;
		   }
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequestDTO authenticationRequest) throws Exception {
 	
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		Admin admin = this.userDetailsService.findByEmail(authenticationRequest.getEmail());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		UserBasicInfo userInfo = (UserBasicInfo) this.dtoUtil.convertToDto(admin, new UserBasicInfo());
		return ResponseEntity.ok(new SigninResponseDTO(token, userInfo));
		
	}
	
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?> saveUser(@Valid @RequestBody AdminSignupRequestDTO admin,BindingResult bindingResult) throws Exception {
		   try {			   
		      boolean feedback = false;
			   feedback =   this.userDetailsService.save(admin);
			   if(feedback) {
				   return ResponseEntity.ok("OK");
			   }
			       return ResponseEntity.ok(null);
			   
		   }
		   catch(Exception ex) {
			   throw ex;
		   }
	}
	
	private void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (Exception ex) {
			throw ex;
		}
	}
}
