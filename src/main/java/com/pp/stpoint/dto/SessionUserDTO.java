package com.pp.stpoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
/**
 *
 * @author Leonardo de Freitas Oliveira
 *  ** enabled ** grantedAuthority ** password
 *  UserController
 */
public class SessionUserDTO {

    private Long id;
    private String name;
    private String email;
    @JsonProperty("accountType")
    private String accountType;
    private String gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;
    private String authority;
    private String language;
    @JsonProperty("desiredLanguage")
    private String desiredLanguage;
    @JsonProperty("profileImageURL")
    private String profileImageURL;

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
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

    /**
     * @return the profileImageURL
     */
    public String getProfileImageURL() {
        return profileImageURL;
    }

    /**
     * @param profileImageURL the profileImageURL to set
     */
    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }
}
