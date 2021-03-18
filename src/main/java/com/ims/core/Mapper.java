package com.ims.core;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Mapper {
	
	   ModelMapper m1 = new ModelMapper();
	   
	   public DTOEntity convertToDto(Object object, DTOEntity mapper) {
   		      m1.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		      return m1.map(object, mapper.getClass());
	   }
	   
	   public Object convertToEntity(Object object, DTOEntity mapper) {
		      return m1.map(mapper, object.getClass());
	   } 

}
