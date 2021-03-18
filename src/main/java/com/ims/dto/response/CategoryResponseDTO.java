package com.ims.dto.response;

import com.ims.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
public class CategoryResponseDTO implements DTOEntity{
	
	   private long id;
	   private String name;

}
