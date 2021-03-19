package com.ims.dto.response;

import com.ims.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO implements DTOEntity {
	   
	   private long id;
	   private String imgUrl;
	   private int imgUrlPrefixId;
}
