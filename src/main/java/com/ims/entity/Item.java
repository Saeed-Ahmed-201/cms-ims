package com.ims.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "item")
public class Item {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
	   @Column(name = "url", length = 45)
	   private String imgUrl;

	   @Column(name = "date")
	   private LocalDate date;
	   
	   @ManyToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name = "language_id", nullable = false)
	   @JsonBackReference
	   private Language language;
	   
	   
	   @ManyToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name = "category_id", nullable = false)
	   @JsonBackReference
	   private Category category;

}
