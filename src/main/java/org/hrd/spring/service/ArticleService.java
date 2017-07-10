package org.hrd.spring.service;

import java.util.List;

import org.hrd.spring.entities.Article;
import org.hrd.spring.entities.filters.ArticleFilter;

public interface ArticleService {
	
	public boolean save(Article article);
	public List<Article> findAllArticleByStatus(String status);
	public List<Article> articleFilter(ArticleFilter article);
}
