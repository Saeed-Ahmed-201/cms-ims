package com.ims.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.core.Mapper;
import com.ims.dto.response.CategoryResponseDTO;
import com.ims.entity.Category;
import com.ims.repository.CategoryRepository;

@Service
public class CategoryService {

	   @Autowired
	   private CategoryRepository categoryRepository;
	   
	   private Mapper dtoUtil = new Mapper();
	   
	   public List<CategoryResponseDTO> retrieveAllCategories(){
		      try {
		    	  List<CategoryResponseDTO> categoryList = new ArrayList<>();
		    	  List<Category> categories = this.categoryRepository.findAll();
		    	  if(!categories.isEmpty()) {
		    		  categories.forEach(category ->{
		    			     categoryList.add((CategoryResponseDTO) this.dtoUtil.convertToDto(category, new CategoryResponseDTO()));
		    		  });
		    	  }
		    	  return categoryList;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
}
