package com.mico.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mico.project.domain.Article;
import com.mico.project.domain.Timeline;
import com.mico.project.model.TimelineResponseBody;
import com.mico.project.service.TimelineServiceImpl;

@Controller
@RequestMapping(value="/timeline")
public class TimelineController {

	private static final Logger logger = LoggerFactory.getLogger(TimelineController.class);
	
	@Autowired
	TimelineServiceImpl timelineService;
	
	@RequestMapping(value="")
	public String timeline(){
	  return "timeline";
	}
	
	@PostMapping("/list")
    public ResponseEntity<?> showTimeline(
            @Valid @RequestBody Timeline timeline, Errors errors) {
		System.out.println("@@@ timeline/list controller");
		String username = timeline.getUsername();
		System.out.println("@@@ : username : "+username);
        TimelineResponseBody result = new TimelineResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        List<Timeline> timelineList = timelineService.findByUsername(username);
        if (timelineList.isEmpty()) {
            result.setMsg("no timeline list found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(timelineList);

        return ResponseEntity.ok(result);

    }
	
	@PostMapping("/addSubject")
    public ResponseEntity<?> addTimeline(
            @Valid @RequestBody Article article, Errors errors) {
		System.out.println("@@@ timeline/list controller");
		System.out.println("@@@ article title : "+article.getTitle());
		Timeline addTimeline = new Timeline("degan", "test1");
		List<Article> articleList = new ArrayList<>();
		articleList.add(article);
		timelineService.saveTimeline(addTimeline, articleList);
        return ResponseEntity.ok("success");

    }
}
