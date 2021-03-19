package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ims.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	   @Query("SELECT COUNT(admin) FROM Admin as admin")
	   public int countAccounts();
	
	   public Admin findByEmail(String email);
}
