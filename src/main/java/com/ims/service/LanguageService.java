package com.ims.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.core.Mapper;
import com.ims.dto.response.LanguageResponseDTO;
import com.ims.entity.Language;
import com.ims.repository.LanguageRepository;

@Service
public class LanguageService {
	
	   @Autowired
	   private LanguageRepository languageRepository;
	
	   private Mapper dtoUtil = new Mapper();
	   
	   public List<LanguageResponseDTO> retrieveAllLanguages(){
		      try {
		    	  List<LanguageResponseDTO> languagesResponseDTO = new ArrayList<>();
		    	  List<Language> languages = this.languageRepository.findAll();
		    	  if(!languages.isEmpty()) {
		    		languages.forEach(language ->{
		    			languagesResponseDTO.add((LanguageResponseDTO) this.dtoUtil.convertToDto(language, new LanguageResponseDTO()));
		    		});
		    	  }
		    	  return languagesResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	
}
