package com.mico.project;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mico.project.domain.Article;
import com.mico.project.domain.Timeline;
import com.mico.project.service.TimelineServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTimeline {
	
	@Autowired
	TimelineServiceImpl timelineService;
	
	String subject;
	String username;
	
	@Before
	public void setting() {
		System.out.println("테스트 시작");
		
		subject = "subject";
		username = "degan";
	}
	
//	@Ignore
	@Test
//	@Transactional
	public void a_test_save_timeline() {
		Timeline addTimeline = new Timeline(username, subject);
		Article article1 = new Article("title1", "slug1", "teaser1", "2018.01.26");
		Article article2 = new Article("title2", "slug2", "teaser2", "2018.01.26");
		Article article3 = new Article("title3", "slug3", "teaser3", "2018.01.26");
		
		Timeline addTimeline2 = new Timeline("degan2", "sub");
		Article article2_1 = new Article("title1", "slug1", "teaser1", "2018.01.26");
		Article article2_2 = new Article("title2", "slug2", "teaser2", "2018.01.26");
		Article article2_3 = new Article("title3", "slug3", "teaser3", "2018.01.26");
		
		List<Article> articleList = new ArrayList<>();
		articleList.add(article1);
		articleList.add(article2);
		articleList.add(article3);
		
		List<Article> articleList2 = new ArrayList<>();
		articleList2.add(article2_1);
		articleList2.add(article2_2);
		articleList2.add(article2_3);
		
		timelineService.saveTimeline(addTimeline, articleList);
		timelineService.saveTimeline(addTimeline2, articleList2);
		
		Timeline timeline = timelineService.findBySubject(subject);
		assertThat(timeline.getSubject(), is(subject));
		
		Set<Article> articles = timeline.getArticles();
		System.out.println("@@@ size : "+ articles.size());
		timeline.getArticles().stream().filter(x -> "title".equals(x.getTitle()));
		
//		System.out.println("@@@ "+articleResult.g);
		
	}
	
	@Ignore
	@Test
	public void b_test_deleteAll_timeline() {
		String title1 = "title1";
		timelineService.deleteAll();
		
		Timeline timeline = timelineService.findByUsername(username).get(0);
		assertTrue(timeline == null);
//		System.out.println("@@@ delete test : " + timeline.getSubject());
//		Article article = (Article) timeline.getArticles().stream().filter(x -> title1.equals(x.getTitle()));
		
//		List<String> collect = timeline.getArticles().stream().map(Article::getTitle).collect(Collectors.toList());
//		System.out.println("@@@ ---------------");
//		collect.forEach(System.out::println);
	}
	
	@Ignore
	@Test
	public void c_test_deleteByUsername_timeline() {
		timelineService.deleteByUsername(username);
		Timeline timeline = timelineService.findByUsername(username).get(0);
		assertTrue(timeline == null);
		
		Timeline timeline2 = timelineService.findByUsername("degan2").get(0);
		assertTrue(timeline2 != null);
	}
	
	@Test
	public void d_test_findArticle_article() {
		Article article = timelineService.articlefindById(4L);
		assertTrue(article != null);
		assertThat(article.getTitle(), is("title2"));
		System.out.println("@@@ "+article.getTitle());
	}
	
//	@Ignore
//	@Test
//	public void e_test_deleteArticle_article() {
//		Article article = timelineService.articlefindById(4L);
//		timelineService.deleteArticle(article);
//		
//		article = timelineService.articlefindById(4L);
//		assertTrue(article == null);
//	}
	
	@Test
	public void f_test_findArticle_timeline() {
		Set<Article> articles = timelineService.findByUsername("degan2").get(0).getArticles();
		assertTrue(articles != null);
	}
	
//	@Test
//	public void e_test_deleteArticle_timeline() {
//		List<Article> articles = timelineService.findByUsername("degan2").getArticles();
//		Article article = articles.get(0);
//		System.out.println("@@@ select title :"+article.getTitle());
//		timelineService.deleteArticle(article);
//		articles = timelineService.findByUsername("degan2").getArticles();
//		assertTrue(articles.size() == 2);
//	}
	
//	@Test
//	public void f_test_findAll_tiemline() {
//		
//	}
}
