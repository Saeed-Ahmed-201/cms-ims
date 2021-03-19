package com.ims.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SigninResponseDTO {
	
	   private String token;
	   private UserBasicInfo info;

}
