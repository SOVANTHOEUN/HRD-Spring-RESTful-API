package org.hrd.spring.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.hrd.spring.entities.Article;
import org.hrd.spring.entities.filters.ArticleFilter;
import org.hrd.spring.repositories.ArticleRepository;
import org.hrd.spring.repositories.FileRepository;
import org.hrd.spring.repositories.TagRepository;
import org.hrd.spring.service.ArticleService;


@Service
public class ArticleServiceImpl implements ArticleService {
	private ArticleRepository articleRepo;
	private FileRepository fileRepo;
	private TagRepository tagRepo;
	
	@Autowired
	public  ArticleServiceImpl(ArticleRepository articleRep,FileRepository fileRepo,TagRepository tagRepo ) {
		this.articleRepo = articleRep;
		this.fileRepo = fileRepo;
		this.tagRepo = tagRepo;
	}
	
	@Override
	@Transactional
	public boolean save(Article article) {
		boolean status = false;
		
		//TODO: Insert Article
		article.setUuid(UUID.randomUUID().toString());
		if(articleRepo.save(article)){
			status = true;
			System.out.println("Article ID "+ article.getId() +" has been inserted!");
			
			// TODO: Insert Files 
			if(article.getFiles() != null){
				if(fileRepo.saveFiles(article.getFiles(),article.getId())){
					System.out.println("This article has "+ article.getFiles().size() + " file(s).");
				}
			}else{
				System.out.println("This article has no file");
			}
			
			// TODO: Insert Tags
			if(article.getTags() != null){
				if(tagRepo.saveTags(article.getTags(), article.getId())){
					System.out.println("This article has "+ article.getTags().size() + " tag(s).");
				}
			}else{
				System.out.println("This article has no tag");
			}
		}
		return status;
	}

	@Override
	public List<Article> findAllArticleByStatus(String status) {
		return articleRepo.findAllArticleByStatus(status);
	}

	@Override
	public List<Article> articleFilter(ArticleFilter article) {
		return articleRepo.articleFilter(article);
	}
	
}
