package com.ims.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdatePasswordRequestDTO {
	
	   private String oldPassword;
	   private String newPassword;

}
