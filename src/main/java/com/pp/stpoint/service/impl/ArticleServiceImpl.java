package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.Article;
import com.pp.stpoint.repository.ArticleRepository;
import com.pp.stpoint.service.ArticleService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;
    
    @Override
    public Article save(Article article) {
        return articleRepository.saveAndFlush(article);
    }

    @Override
    public Article update(Article article, Long id) {
        return articleRepository.saveAndFlush(article);
    }

    @Override
    public void remove(Long id) {
        articleRepository.delete(id);
    }

    @Override
    public Article find(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public Collection<Article> all() {
        return articleRepository.findAll();
    }
    
}
