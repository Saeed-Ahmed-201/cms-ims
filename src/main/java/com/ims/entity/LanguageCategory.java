package com.ims.entity;


import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ims.keys.LanguageCategoryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "language_category")
public class LanguageCategory {

	   @EmbeddedId
	   private LanguageCategoryKey id;
	   
	   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinColumns(@JoinColumn(name = "language_id", referencedColumnName = "id", updatable = false, insertable = false, nullable = false))
	   @JsonBackReference
	   private Language languages;
	   
	   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinColumns(@JoinColumn(name = "category_id", referencedColumnName = "id", updatable = false, insertable = false, nullable = false))
	   @JsonBackReference
	   private Category categories;
	   
}
