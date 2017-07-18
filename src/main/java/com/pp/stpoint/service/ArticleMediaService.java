package com.pp.stpoint.service;

import com.pp.stpoint.entity.ArticleMedia;
import java.util.Collection;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ArticleMediaService {
    ArticleMedia save(ArticleMedia articleMedia);
    ArticleMedia update(ArticleMedia articleMedia, Long id);
    void remove(Long id);
    ArticleMedia find(Long id);
    Collection<ArticleMedia> all();
}
