package com.pp.stpoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 *
 * @author Leonardo de Freitas Oliveira
 * 
 *  ** enabled ** grantedAuthority **profileImageURL  ** accountType  ** email
 *  UserController
 */
public class ProfileDTO {
    private Long id;
    private String name;
    private String gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;
    private String language;
    @JsonProperty("desiredLanguage")
    private String desiredLanguage;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the birth
     */
    public LocalDate getBirth() {
        return birth;
    }

    /**
     * @param birth the birth to set
     */
    public void setBirth(LocalDate birth) {
        this.birth = birth;
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
     * @return the desiredLanguage
     */
    public String getDesiredLanguage() {
        return desiredLanguage;
    }

    /**
     * @param desiredLanguage the desiredLanguage to set
     */
    public void setDesiredLanguage(String desiredLanguage) {
        this.desiredLanguage = desiredLanguage;
    }

}
