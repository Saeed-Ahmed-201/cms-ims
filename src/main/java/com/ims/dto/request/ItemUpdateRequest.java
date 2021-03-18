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
public class ItemUpdateRequest implements DTOEntity {
	
	   private long itemId;
	   private long languageId;
	   private long categoryId;

}
