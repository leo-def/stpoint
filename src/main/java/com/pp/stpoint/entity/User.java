package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.persistence.Column;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@JsonIgnoreProperties({"authorities"})
@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7891582033044334646L;

	public static final String PROFILE_IMAGE_FOLDER = "profile_image";
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    @JsonProperty("accountType")
    private String accountType;
    private String gender;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;
    @JsonProperty("grantedAuthority")
    @ManyToOne(cascade = CascadeType.ALL)
    private UserGrantedAuthority grantedAuthority;
    private String language;
    @JsonProperty("desiredLanguage")
    private String desiredLanguage;
    @JsonProperty("profileImageURL")
    private String profileImageURL;
    @Column(name = "enabled")
    private boolean enabled;
    
    public User(){}
    public User(
            String name,
            String email,
            String accountType,
            UserGrantedAuthority grantedAuthority,
            boolean enabled){
        this.name = name;
        this.email = email;
        this.accountType = accountType;
        this.grantedAuthority = grantedAuthority;
        this.enabled = enabled;
    }
    public User(
            String name,
            String email,
            String accountType,
            String gender,
            UserGrantedAuthority grantedAuthority,
            String language,
            String desiredLanguage,
            String profileImageURL,
            boolean enabled){
        this.name = name;
        this.email = email;
        this.accountType = accountType;
        this.gender = gender;
        this.grantedAuthority = grantedAuthority;
        this.language= language;
        this.desiredLanguage= desiredLanguage;
        this.profileImageURL= profileImageURL;
        this.enabled = enabled;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(getGrantedAuthority());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
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
    @Override
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
     * @return the role
     */
    public UserGrantedAuthority getGrantedAuthority() {
        return grantedAuthority;
    }

    /**
     * @param role the role to set
     */
    public void setGrantedAuthority(UserGrantedAuthority role) {
        this.grantedAuthority = role;
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

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
