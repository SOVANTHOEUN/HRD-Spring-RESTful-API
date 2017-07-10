package org.hrd.spring.controller;

import java.util.List;

import org.hrd.spring.entities.Article;
import org.hrd.spring.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestArticleController {

	private ArticleService articleService;
	
	@Autowired
	public TestArticleController(ArticleService articleService){
		this.articleService = articleService;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/test", "/search"}, method = RequestMethod.POST)
	public boolean save(@RequestBody Article article){
		return articleService.save(article);
	}
	
	@ResponseBody
	@RequestMapping(value = "/find-all-by-status", method = RequestMethod.GET)
	public List<Article> findAllByStatus() {
		return articleService.findAllArticleByStatus("1");
	}
	
}
