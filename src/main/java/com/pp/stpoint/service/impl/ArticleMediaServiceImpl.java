package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.ArticleMedia;
import com.pp.stpoint.repository.ArticleMediaRepository;
import com.pp.stpoint.service.ArticleMediaService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class ArticleMediaServiceImpl implements ArticleMediaService{

    @Autowired
    ArticleMediaRepository articleMediaRepository;
    
    @Override
    public ArticleMedia save(ArticleMedia articleMedia) {
        return articleMediaRepository.saveAndFlush(articleMedia);
    }

    @Override
    public ArticleMedia update(ArticleMedia articleMedia, Long id) {
        return articleMediaRepository.saveAndFlush(articleMedia);
    }

    @Override
    public void remove(Long id) {
        articleMediaRepository.delete(id);
    }

    @Override
    public ArticleMedia find(Long id) {
        return articleMediaRepository.findOne(id);
    }

    @Override
    public Collection<ArticleMedia> all() {
        return articleMediaRepository.findAll();
    }
    
}
