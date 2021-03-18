package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>{
	
	    

}
