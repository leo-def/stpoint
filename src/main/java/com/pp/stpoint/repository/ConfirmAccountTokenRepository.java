package com.pp.stpoint.repository;

import com.pp.stpoint.entity.ConfirmAccountToken;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ConfirmAccountTokenRepository extends JpaRepository<ConfirmAccountToken, Long>{
    ConfirmAccountToken findByToken(String token);
    Collection<ConfirmAccountToken> findByUserId(Long id);
}
