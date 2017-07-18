package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Entity
public class Situation {

    public static final String IMAGE_FOLDER = "image";

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @JsonProperty("accountType")
    private String accountType;
    
    private String slug;
    @JsonProperty("imageURL")
    private String imageURL;
    @ManyToOne(optional = true)
    private Situation parent;
    
    @OneToMany(cascade={CascadeType.REMOVE})
    @JoinColumn(name = "SITUATION_ID")
    private List<Value> values = new ArrayList<>();
    
    @OneToMany(cascade = {CascadeType.REMOVE})
    @JsonManagedReference
    private List<Expression> expressions = new ArrayList<>();
    
    @OneToMany(cascade = {CascadeType.REMOVE})
    @JsonManagedReference
    private List<Article> articles = new ArrayList<>();

    /**
     * @return object to string
     */
    @Override
    public String toString() {
        return title;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the authorization
     */
    public String getAuthorization() {
        return accountType;
    }

    /**
     * @param authorization the authorization to set
     */
    public void setAuthorization(String authorization) {
        this.accountType = authorization;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug the slug to set
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * @return the parent
     */
    public Situation getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Situation parent) {
        this.parent = parent;
    }
    
    
    /**
     * @return the values
     */
    public Collection<Value> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<Value> values) {
        this.values = values;
    }
    
    
    /**
     * @return the expressions
     */
    public List<Expression> getExpressions() {
        return expressions;
    }

    /**
     * @param expressions the expressions to set
     */
    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
    
    /**
     * @return the articles
     */
    public Collection<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
