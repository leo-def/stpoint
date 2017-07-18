package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Entity
public class ArticleMedia {

	public static final String FOLDER = "article_media";
	
    @Id
    @GeneratedValue
    private Long id;
	
    private String url;

    @ManyToOne(optional=true)
    @JsonIgnore
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;
	

	@Override
    public String toString() {
        return url;
    }

    public ArticleMedia() {}

    public ArticleMedia(Long id) {
        this.id = id;
    }
	
	public ArticleMedia(String url) {
		this.url = url;
    }

    public ArticleMedia(Long id, String url) {
        this.id = id;
        this.url = url;
    }
	
	public ArticleMedia(Article article) {
        this.article = article;
    }
	
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the url
     */
    public String getURL() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setURL(String url) {
        this.url = url;
    }

   
	/**
     * @return the article
     */
	public Article getArticle() {
		return article;
	}
	
	/**
     * @param article the article to set
     */
	public void setArticle(Article article) {
		this.article = article;
	}

}
