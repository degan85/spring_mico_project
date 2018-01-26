package com.mico.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.mico.project.domain.Timeline;

@Transactional
@RepositoryRestResource(collectionResourceRel = "Timeline", path = "Timeline")
public interface TimelineRepository extends JpaRepository<Timeline, Long>{
	public Timeline findBySubject(String subject);
	public List<Timeline> findByUsername(String username);
	public void deleteAll();
//	public void delete(Article article);
	public void deleteByUsername(String username);
//	public Article findByUsernameAndTitle(String username, String title);
	
}
