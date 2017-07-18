package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.Collection;
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
public class Expression {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    
    @OneToMany(cascade={CascadeType.REMOVE})
    @JoinColumn(name = "EXPRESSION_ID")
    private Collection<Value> values =  new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "SITUATION_ID")
    private Situation situation;

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
     * @return the situations
     */
    public Situation getSituation() {
        return situation;
    }

    /**
     * @param situations the situations to set
     */
    public void setSituation(Situation situation) {
        this.situation = situation;
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
     * @return the values
     */
    public Collection<Value> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(Collection<Value> values) {
        this.values = values;
    }
}
