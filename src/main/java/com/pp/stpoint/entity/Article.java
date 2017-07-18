package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Entity
public class Article {
	
    @Id
    @GeneratedValue
    private Long id;
	
    private String language;
	
    private String value;
 

    @ManyToOne(optional=true)
    @JsonIgnore
    @JoinColumn(name = "SITUATION_ID")
    private Situation situation;
	
	
	@OneToMany(cascade={CascadeType.REMOVE})
    @JoinColumn(name = "ARTICLE_ID")
    private List<ArticleMedia> media = new ArrayList<ArticleMedia>();

	@Override
    public String toString() {
        return value;
    }

    public Article() {}

    public Article(Long id) {
        this.id = id;
    }

    public Article(String language, String value) {
        this.language = language;
        this.value = value;
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
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
	
	/**
     * @return the situation
     */
	public Situation getSituation() {
		return situation;
	}
	
	/**
     * @param media the media to set
     */
	public void setMedia(List<ArticleMedia>  media) {
		this.media = media;
	}

	/**
     * @return the media
     */
	public List<ArticleMedia>  getMedia() {
		return media;
	}
	
	/**
     * @param situation the situation to set
     */
	public void setSituation(Situation situation) {
		this.situation = situation;
	}
}
