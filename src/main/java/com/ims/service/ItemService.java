package com.ims.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exceptions.CustomException;
import com.ims.core.Mapper;
import com.ims.dto.request.ItemUpdateRequest;
import com.ims.dto.request.ItemsDeleteRequest;
import com.ims.dto.response.CategoryStaticResponseDTO;
import com.ims.dto.response.CategoryStatics;
import com.ims.dto.response.ItemResponseDTO;
import com.ims.dto.response.LanguageStaticResponse;
import com.ims.entity.Category;
import com.ims.entity.Item;
import com.ims.entity.Language;
import com.ims.repository.CategoryRepository;
import com.ims.repository.ItemRepository;
import com.ims.repository.LanguageRepository;

@Service
public class ItemService {

	   @Autowired
	   private ItemRepository itemRepository;
	   
	   @Autowired
	   private CategoryRepository categoryRepository;
	   
	   @Autowired
	   private LanguageRepository languageRepository;
	   
	   private Mapper dtoUtil = new Mapper();
	   
	   public List<ItemResponseDTO> retrieveAllItems(Pageable pagination) throws CustomException{
		      try {
		    	  List<ItemResponseDTO> itemResponseDTO = new ArrayList<>();
		    	  Page<Item> items = this.itemRepository.findAll(pagination);
		    	  if(!items.isEmpty()) {
		    		  for(Item item : items.getContent())
		    			 itemResponseDTO.add((ItemResponseDTO) this.dtoUtil.convertToDto(item, new ItemResponseDTO()));		    		 
		    	  }
		    	  return itemResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
	   
	   public List<ItemResponseDTO> retrieveItemsByCategoryAndLanguage(long languageId, long categoryId, Pageable pagination) throws CustomException{
		      try {
		    	  List<ItemResponseDTO> itemResponseDTO = new ArrayList<>();
		    	  Page<Item> items = this.itemRepository.findItemByCategoryAndLanguage(languageId, categoryId, pagination);
		    	  if(!items.isEmpty()) {
		    		  items.getContent().forEach(item->{
		    			  itemResponseDTO.add((ItemResponseDTO) this.dtoUtil.convertToDto(item, new ItemResponseDTO()));		    		 		    			  
		    		  });
		    	  }
		    	  return itemResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
	   
	   public boolean deleteMultipleItems(ItemsDeleteRequest ids) throws CustomException{
		      try {
		    	  boolean feedback = false;
		    	  if(!ids.getIds().isEmpty()) {
		    		  ids.getIds().forEach(id -> {
		    			  this.itemRepository.deleteById(id);
		    		  });
		    		  feedback = true;
		    	  }
		    	  return feedback;
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
	   
	   public boolean updateItemLanguageCategory(ItemUpdateRequest updateItem) throws CustomException{
		      try {
		    	  boolean feedback = false;
		    	  Item item = this.itemRepository.findById(updateItem.getItemId()).orElse(null);
		    	  if(!item.equals(null)) {
		    		  Category category = this.categoryRepository.findById(updateItem.getCategoryId()).orElse(null);
		    		  Language language = this.languageRepository.findById(updateItem.getLanguageId()).orElse(null);
		    		  item.setCategory(category);
		    		  item.setLanguage(language);
		    		  System.out.println(this.itemRepository.save(item));
		    		  feedback = true;
		    	  }
		    	  return feedback;
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
	   
	   public List<LanguageStaticResponse> itemStaticsByLanguage() throws CustomException{
		      try {
		    	  List<LanguageStaticResponse> languageStaticResponse = new ArrayList<>();
		    	  List<Language> languages = this.languageRepository.findAll();
		    	  if(!languages.isEmpty()) {
		    		  languages.forEach(language -> {		    			  
			    		  long count = this.itemRepository.countByLanguage(language.getId());
			    		  if(count > 0) {
			    			  LanguageStaticResponse languageStatics = new LanguageStaticResponse();
				    		  languageStatics.setName(language.getName());
				    		  languageStatics.setTotalItems(count); 
				    		  
				    		  List<Category> categories = this.categoryRepository.findAll();
				    		  List<CategoryStatics> languageCategoryStatics = new ArrayList<>();
				    		  categories.forEach(category -> {			    			  
				    			 long countCategory =  this.itemRepository.countByCategory(language.getId(), category.getId());
				    			 if(countCategory > 0) {
				    				 languageCategoryStatics.add(new CategoryStatics(category.getName(), countCategory));
				    			 }
				    		  });
				    		  languageStatics.setCategories(languageCategoryStatics);
				    		  languageStaticResponse.add(languageStatics);
			    		  }
		    		  });		    		  
		    	  }
		    	  return languageStaticResponse;
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
	   
	   public CategoryStaticResponseDTO itemStaticsByCategory() throws CustomException{
		      try {
		    	  CategoryStaticResponseDTO categoryResponseDTO = new CategoryStaticResponseDTO();
		    	  long totalCategoryCount = this.itemRepository.countItemByCategory();
		    	  if(totalCategoryCount > 0) {
		    		  List<CategoryStatics> categoryStaticsList = new ArrayList<>();
		    		  categoryResponseDTO.setTotalItems(totalCategoryCount);
		    		  List<Category> categories = this.categoryRepository.findAll();
		    		  categories.forEach(category -> {
		    			  long singleCategoryCount = this.itemRepository.countItemBySingleCategory(category.getId());
		    			  if(singleCategoryCount > 0) {
		    				  categoryStaticsList.add(new CategoryStatics(category.getName(), singleCategoryCount));
		    			  }
		    		  });
		    		  categoryResponseDTO.setCategories(categoryStaticsList);
		    	  }
		    	  return categoryResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw new CustomException("null");
		      }
	   }
}
