package com.mico.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.mico.project.domain.Article;

@Transactional
@RepositoryRestResource(collectionResourceRel = "Article", path = "Article")
public interface ArticleRepository extends JpaRepository<Article, Long>{

	public Article findById(Long id);
	public void delete(Article article);
}
