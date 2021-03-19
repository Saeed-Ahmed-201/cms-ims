package com.ims.dto.request;

import com.ims.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInRequestDTO implements DTOEntity {
	
	   private String email;
	   private String password;

}
