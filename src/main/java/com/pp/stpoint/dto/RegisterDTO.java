package com.pp.stpoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pp.stpoint.constants.AccountTypes;
import com.pp.stpoint.constants.Authorities;
import java.time.LocalDate;

/**
 *
 * @author Leonardo de Freitas Oliveira
 * 
 *  ** password  ** enabled ** grantedAuthority **profileImageURL  ** accountType
 *  UserController
 */
public final class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;
    @JsonProperty("accountType")
    private String accountType = AccountTypes.PUBLIC.accountType;
    @JsonProperty("grantedAuthority")
    private String grantedAuthority = Authorities.USER.authority;
    private String language;
    @JsonProperty("desiredLanguage")
    private String desiredLanguage;

    public RegisterDTO() {
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the grantedAuthority
     */
    public String getGrantedAuthority() {
        return Authorities.USER.authority;
    }

    /**
     * @param grantedAuthority the grantedAuthority to set
     */
    public void setGrantedAuthority(String grantedAuthority) {
        this.grantedAuthority = Authorities.USER.authority;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return AccountTypes.PUBLIC.accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = AccountTypes.PUBLIC.accountType;
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
