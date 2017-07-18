package com.pp.stpoint.service.impl;

import com.pp.stpoint.constants.AccountTypes;
import com.pp.stpoint.entity.Situation;
import com.pp.stpoint.repository.SituationRepository;
import com.pp.stpoint.service.SituationService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class SituationServiceImpl implements SituationService{

    @Autowired
    SituationRepository situationRepository;
    
    @Override
    public Situation save(Situation situation) {
        return situationRepository.saveAndFlush(situation);
    }

    @Override
    public Situation update(Situation situation, Long id) {
        situation.setId(id);
        return situationRepository.saveAndFlush(situation);
    }
    
    @Override
    public void remove(Long id) {
        situationRepository.delete(id);
    }

    @Override
    public Situation find(Long id) {
        return situationRepository.findOne(id);
    }

    @Override
    public Collection<Situation> all() {
        return situationRepository.findAll();
    }

    @Override
    public Collection<Situation> byAccountType(String accountType) {
        return situationRepository.findByAccountTypeIn(AccountTypes.lessThan(accountType));
    }

	@Override
	public Collection<Object[]> asItem() {
		return situationRepository.findIdAndTitleFromParents();
	}
    
	@Override
	public Collection<Situation> parents() {
		return situationRepository.findByNullParent();
	}
	
	@Override
	public Collection<Situation> byParent(Long id) {
		return situationRepository.findByParentId(id);
	}

	@Override
	public Collection<Situation> parentsByAccountType(String accountType) {
		return situationRepository.findByNullParentAndAccountType(accountType);
	}

	@Override
	public Collection<Situation> byParentAndByAccountType(Long id, String accountType) {
		return situationRepository.findByParentIdAndAccountType(id, accountType);
	}
	
}
