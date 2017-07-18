package com.pp.stpoint.service;

import com.pp.stpoint.entity.Situation;
import java.util.Collection;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface SituationService {
    Situation save(Situation situation);   
    Situation update(Situation situation, Long id);
    void remove(Long id);
    Situation find(Long id);
    Collection<Situation> all();
    Collection<Situation> byAccountType(String accountType);
	Collection<Object[]> asItem();
	Collection<Situation> parents(); 
	Collection<Situation> byParent(Long id);
	Collection<Situation> parentsByAccountType(String accountType);
	Collection<Situation> byParentAndByAccountType(Long id, String accountType);
}
