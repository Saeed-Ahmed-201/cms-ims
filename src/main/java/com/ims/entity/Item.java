package com.ims.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	   @Temporal(TemporalType.TIMESTAMP)
	   @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	   private Date date;
	   
	   @Column(name = "img_url_prefix_id")
	   private int imgUrlPrefixId;
	   
	   @ManyToOne
	   @JoinColumn(name = "language_id", nullable = false)
	   @JsonBackReference
	   private Language language;
	   
	   
	   @ManyToOne
	   @JoinColumn(name = "category_id", nullable = false)
	   @JsonBackReference
	   private Category category;

}
