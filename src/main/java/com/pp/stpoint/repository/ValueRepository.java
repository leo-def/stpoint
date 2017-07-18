package com.pp.stpoint.repository;

import com.pp.stpoint.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ValueRepository extends JpaRepository<Value, Long>{
    
}
