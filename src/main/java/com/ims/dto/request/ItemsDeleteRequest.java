package com.ims.dto.request;

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
public class ItemsDeleteRequest implements DTOEntity{

	   private List<Long> ids;
}
