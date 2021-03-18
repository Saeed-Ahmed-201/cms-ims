package com.ims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.request.ItemUpdateRequest;
import com.ims.dto.request.ItemsDeleteRequest;
import com.ims.dto.response.CategoryStaticResponseDTO;
import com.ims.dto.response.ItemResponseDTO;
import com.ims.dto.response.LanguageStaticResponse;
import com.ims.service.ItemService;

@RestController
public class ItemController {
	
	   @Autowired
	   private ItemService itemService;
	   
	   @GetMapping(value = "/allItems")
	   public ResponseEntity<?> retrieveAllItems(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "40") int size){
		      try {
		    	    Map<String, List<ItemResponseDTO>> itemResponseMap = new HashMap<>();
		    	    Pageable pagination =  PageRequest.of(page, size);
		    	    List<ItemResponseDTO> items = this.itemService.retrieveAllItems(pagination);
		    	    if(!items.isEmpty()) {
		    	    	itemResponseMap.put("items", items);
		    	    	return ResponseEntity.ok(itemResponseMap);
		    	    }
		    	    return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @GetMapping(value = "/items/{languageId}/{categoryId}")
	   public ResponseEntity<?> retrieveItemsByCategoryAndLanguage(@PathVariable(name = "languageId") long languageId, @PathVariable(name = "categoryId") long categoryId,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "40") int size){
		      try {
		    	    Map<String, List<ItemResponseDTO>> itemResponseMap = new HashMap<>();
		    	    Pageable pagination =  PageRequest.of(page, size);
		    	    List<ItemResponseDTO> items = this.itemService.retrieveItemsByCategoryAndLanguage(languageId, categoryId, pagination);
		    	    if(!items.isEmpty()) {
		    	    	itemResponseMap.put("items", items);
		    	    	return ResponseEntity.ok(itemResponseMap);
		    	    }
		    	    return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @PutMapping(value = "/delete/items")
	   public ResponseEntity<?> deleteMultipleItems(@RequestBody ItemsDeleteRequest ids){
		      try {
		    	  boolean feedback = this.itemService.deleteMultipleItems(ids);
		    	  if(feedback) {
		    		  return ResponseEntity.ok("ok");
		    	  }
		    	      return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @PutMapping(value = "/update/item")
	   public ResponseEntity<?> updateItemLanguageCategory(@RequestBody ItemUpdateRequest updateItem){
		      try {
		    	  boolean feedback = this.itemService.updateItemLanguageCategory(updateItem);
		    	  if(feedback) {
		    		  return ResponseEntity.ok("ok");
		    	  }
		    	  return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw ex;  
		      }
	   }
	   
	   @GetMapping(value = "/languagesStatistics")
	   public ResponseEntity<?> itemStaticsByLanguage() {
		      try {
		    	  Map<String, List<LanguageStaticResponse>> languageStaticsMap = new HashMap<>();
		    	  List<LanguageStaticResponse> languageStatics =  this.itemService.itemStaticsByLanguage();
		    	  if(!languageStatics.isEmpty()) {
		    		  languageStaticsMap.put("languages", languageStatics);
		    		  return ResponseEntity.ok(languageStaticsMap);
		    	  }
		    	  return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @GetMapping(value = "/categoriesStatistics")
	   public ResponseEntity<?> itemStaticsByCategory(){
		      try {
		    	  CategoryStaticResponseDTO categoryStaticResponseDTO = this.itemService.itemStaticsByCategory();
		    	  if(!categoryStaticResponseDTO.equals(null)) {		    		  
		    		  return ResponseEntity.ok(categoryStaticResponseDTO);
		    	  }
		    	      return ResponseEntity.ok(null);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   

}
