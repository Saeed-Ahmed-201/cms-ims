package com.ims.dto.request;

import com.ims.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdminSignupRequestDTO implements DTOEntity{
	
	   private String email;
	   private String password;

}
