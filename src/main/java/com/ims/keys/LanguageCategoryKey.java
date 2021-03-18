package com.ims.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class LanguageCategoryKey implements Serializable {

       @Column(name = "language_id")
	   private Long languageId;
	   @Column(name = "category_id")
	   private Long CategoryId;
}
