/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pp.stpoint.repository;

import com.pp.stpoint.entity.UserGrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 80515304
 */
public interface UserGrantedAuthorityRepository extends JpaRepository<UserGrantedAuthority, Long>{
    Long countByAuthority(String authority);
}
