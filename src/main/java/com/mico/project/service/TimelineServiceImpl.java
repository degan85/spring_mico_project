package com.mico.project.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mico.project.domain.Article;
import com.mico.project.domain.Timeline;
import com.mico.project.repository.ArticleRepository;
import com.mico.project.repository.TimelineRepository;

@Service
public class TimelineServiceImpl {

	@Autowired
	TimelineRepository timelineRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	public void saveTimeline(Timeline timeline, List<Article> articles){
		Set<Article> articlesSet = new HashSet<Article>();
		for(Article article:articles){
			articlesSet.add(article);
		}
		timeline.setArticles(articlesSet);
		timelineRepository.save(timeline);
    }
	
	public Timeline findBySubject(String subject) {
		return timelineRepository.findBySubject(subject);
	}
	
	public Timeline findAll() {
		return (Timeline) timelineRepository.findAll();
	}

	public List<Timeline> findByUsername(String username) {
		return timelineRepository.findByUsername(username);
	}
	
	public void deleteAll() {
		timelineRepository.deleteAll();
	}
	
	public void deleteByUsername(String username) {
		timelineRepository.deleteByUsername(username);
	}
	
	public Article articlefindById(Long id) {
		return articleRepository.findById(id);
	}
	
//	public void deleteArticle(Article article) {
//		articleRepository.delete(article);
//	}
}
