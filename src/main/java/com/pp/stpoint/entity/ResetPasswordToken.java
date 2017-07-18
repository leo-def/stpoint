package com.pp.stpoint.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Entity
public class ResetPasswordToken {
    private static int EXPIRATION_DAYS = 20;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String token;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
  
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiryDate;

    
    public ResetPasswordToken(){
       generateExpirationDate();
    }

    public ResetPasswordToken(String token){
        this();
        this.token = token;
    }
    
    public ResetPasswordToken(String token, User user){
        this(token);
        this.user = user;
    }
    
    
    public void generateExpirationDate(){
        if(expiryDate != null){return;}
        expiryDate = LocalDate.now().plusDays(EXPIRATION_DAYS);
    }
    
    @Override
    public String toString(){
        return token;
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
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the expiryDate
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
