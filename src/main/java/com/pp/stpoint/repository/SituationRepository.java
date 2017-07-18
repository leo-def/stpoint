package com.pp.stpoint.repository;

import com.pp.stpoint.entity.Situation;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface SituationRepository extends JpaRepository<Situation,Long>{
    public List<Situation> findByAccountTypeIn(Collection<String> types);
    public List<Situation> findByParentId(Long id);
    
    @Query("SELECT s FROM Situation s WHERE s.parent is null")
    public List<Situation> findByNullParent();
    
    @Query("SELECT s FROM Situation s WHERE s.parent is null AND s.accountType = :accountType")
    public List<Situation> findByNullParentAndAccountType(@Param("accountType") String accountType);
    
    @Query("SELECT s FROM Situation s WHERE s.parent.id = :id AND s.accountType = :accountType")
    public List<Situation> findByParentIdAndAccountType(@Param("id") Long id, @Param("accountType") String accountType);
    
    @Query("SELECT s.id, s.title FROM Situation s WHERE s.parent is null")
    public List<Object[]> findIdAndTitleFromParents();
    
    
    
}
