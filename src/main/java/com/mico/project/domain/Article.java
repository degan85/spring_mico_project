package com.mico.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="articles")
@Data
@Getter @Setter @NoArgsConstructor
public class Article {

	  @Id
	  @GeneratedValue
	  private Long id;
	  private String title;
	  private String slug;
	  private String teaser;
	  private String date;

	  public Article(String title, String slug, String teaser, String date){
	    this.title = title;
	    this.slug = slug;
	    this.teaser = teaser;
	    this.date = date;
	  }
}
