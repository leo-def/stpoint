package com.pp.stpoint.repository;

import com.pp.stpoint.entity.ResetPasswordToken;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long>{
    ResetPasswordToken findByToken(String token);
    Collection<ResetPasswordToken> findByUserId(Long id);
}
