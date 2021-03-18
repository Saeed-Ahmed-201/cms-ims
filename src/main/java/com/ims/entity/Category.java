package com.ims.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
       @Column(name = "name", length = 45)
	   private String name;
	   
	   @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	   @JsonManagedReference
	   private List<Item> items; 
	   
	   @OneToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	   @JsonManagedReference
	   private List<LanguageCategory> languageCategories;
}
