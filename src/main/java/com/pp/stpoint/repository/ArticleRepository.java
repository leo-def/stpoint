package com.pp.stpoint.repository;

import com.pp.stpoint.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ArticleRepository extends JpaRepository<Article, Long>{
    
}
