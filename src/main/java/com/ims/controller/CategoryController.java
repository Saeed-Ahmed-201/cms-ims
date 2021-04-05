package com.ims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.CustomException;
import com.ims.dto.response.CategoryResponseDTO;
import com.ims.service.CategoryService;

@RestController
public class CategoryController {

	   @Autowired
	   private CategoryService categoryService;
	   
	   @GetMapping(value = "/allCategories")
	   public ResponseEntity<?> retrieveAllCategories() throws CustomException{
		      try {
		    	  Map<String, List<CategoryResponseDTO>> categoriesResponseMap = new HashMap<>();
		    	  List<CategoryResponseDTO> categoriesList = this.categoryService.retrieveAllCategories();
		    	  if(!categoriesList.isEmpty()) {
		    		  categoriesResponseMap.put("categories", categoriesList);
		    		  return ResponseEntity.ok(categoriesResponseMap);
		    	  }
		    	  return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
}
