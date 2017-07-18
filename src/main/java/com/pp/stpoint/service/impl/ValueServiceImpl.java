package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.Value;
import com.pp.stpoint.repository.ValueRepository;
import com.pp.stpoint.service.ValueService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class ValueServiceImpl implements ValueService{

    @Autowired
    ValueRepository valueRepository;
    
    @Override
    public Value save(Value value) {
        return valueRepository.saveAndFlush(value);
    }

    @Override
    public Value update(Value value, Long id) {
        return valueRepository.saveAndFlush(value);
    }

    @Override
    public void remove(Long id) {
        valueRepository.delete(id);
    }

    @Override
    public Value find(Long id) {
        return valueRepository.findOne(id);
    }

    @Override
    public Collection<Value> all() {
        return valueRepository.findAll();
    }
    
}
