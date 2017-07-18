package com.pp.stpoint.repository;

import com.pp.stpoint.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    List<User> findByGrantedAuthorityAuthority(String authority);
}
