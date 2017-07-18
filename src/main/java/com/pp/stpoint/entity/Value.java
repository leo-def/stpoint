package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Value {

    public static final String AUDIO_FOLDER = "audio";

    @Id
    @GeneratedValue
    private Long id;
    private String language;
    private String value;
    @JsonProperty("audioURL")
    private String audioURL;
 
 
    //buscar alternativa
    @ManyToOne(optional=true)
    @JsonIgnore
    @JoinColumn(name = "SITUATION_ID")
    private Situation situation;
    
    //buscar alternativa
    @ManyToOne(optional=true)
    @JsonIgnore
    @JoinColumn(name = "EXPRESSION_ID")
    private Expression expression;

	@Override
    public String toString() {
        return value;
    }

    public Value() {
    }

    public Value(Long id) {
        this.id = id;
    }

    public Value(String language, String value) {
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
     * @return the audioURL
     */
    public String getAudioURL() {
        return audioURL;
    }

    /**
     * @param audioURL the audioURL to set
     */
    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }
	
	/**
     * @return the situation
     */
	public Situation getSituation() {
		return situation;
	}

	/**
     * @param situation the situation to set
     */
	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	/**
     * @return the expression
     */
	public Expression getExpression() {
		return expression;
	}

	/**
     * @param expression the expression to set
     */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
