/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pp.stpoint.service;

import com.pp.stpoint.entity.Article;
import java.util.Collection;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ArticleService {
    Article save(Article article);
    Article update(Article article, Long id);
    void remove(Long id);
    Article find(Long id);
    Collection<Article> all();
}
