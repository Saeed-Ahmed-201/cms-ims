package com.ims.dto.response;

import java.util.List;

import com.ims.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LanguageStaticResponse implements DTOEntity {
	
	   private String name;
	   private long   totalItems;
	   private List<CategoryStatics> categories;
	   

}
