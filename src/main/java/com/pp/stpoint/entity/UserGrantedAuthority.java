package com.pp.stpoint.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;


/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Entity
public class UserGrantedAuthority implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8419115138516645292L;
	@Id
    @GeneratedValue
    private Long id;
    private String authority;

    public UserGrantedAuthority(){}
    public UserGrantedAuthority(String authority){this.authority = authority;}
    
    @Override
    public String toString(){
        return authority;
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
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * @param title the title to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
