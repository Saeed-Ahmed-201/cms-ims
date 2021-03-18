package com.ims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.response.LanguageResponseDTO;
import com.ims.service.LanguageService;

@RestController
public class LanguageControlelr {

	   @Autowired
	   private LanguageService languageService;
	   
	   @GetMapping(value = "allLanguages")
	   public ResponseEntity<?> retrieveAllLanguages(){
		      try {
		    	  Map<String, List<LanguageResponseDTO>> languagesResponseMap = new HashMap<>();
		    	  List<LanguageResponseDTO> languages = this.languageService.retrieveAllLanguages();
		    	  if(!languages.isEmpty()) {
		    		  languagesResponseMap.put("languages", languages);
		    		  return ResponseEntity.ok(languagesResponseMap);
		    	  }
		    	  else {
		    		  return ResponseEntity.ok(null);
		    	  }
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
}
