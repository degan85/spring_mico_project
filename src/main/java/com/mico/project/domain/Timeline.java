package com.mico.project.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subject")
@Data
@Getter @Setter @NoArgsConstructor
public class Timeline {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String subject;
	  
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "subject_articles",
	joinColumns = @JoinColumn(name = "subject_id"),
	inverseJoinColumns = @JoinColumn(name = "article_id"))
	private Set<Article> articles;
	
	public Timeline(String username, String subject) {
		this.username = username;
		this.subject = subject;
	}
}
