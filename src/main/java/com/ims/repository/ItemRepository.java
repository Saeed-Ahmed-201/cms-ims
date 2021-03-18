package com.ims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	 
	    @Query("SELECT i FROM Item as i JOIN i.language l JOIN i.category c WHERE l.id = :languageId AND c.id = :categoryId")
	    public Page<Item> findItemByCategoryAndLanguage(@Param("languageId") long languageId, @Param("categoryId") long categoryId, Pageable pagignation);

	    @Query("SELECT COUNT(items) FROM Item as items JOIN items.language l WHERE l.id = :languageId")
	    public Long countByLanguage(@Param("languageId") long languageId);	    

	    @Query("SELECT COUNT(items) FROM Item as items JOIN items.language l JOIN items.category c WHERE l.id = :languageId AND c.id = :categoryId")
	    public Long countByCategory(@Param("languageId") long languageId, @Param("categoryId") long categoryId);
	    
	    @Query("SELECT COUNT(items) FROM Item as items JOIN items.category c")
	    public Long countItemByCategory();
	    
	    @Query("SELECT COUNT(items) FROM Item as items JOIN items.category c WHERE c.id = :categoryId")
	    public Long countItemBySingleCategory(@Param("categoryId") long categoryId);
	    
	    
}