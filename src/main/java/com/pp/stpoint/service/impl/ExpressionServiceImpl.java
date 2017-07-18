package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.repository.ExpressionRepository;
import com.pp.stpoint.service.ExpressionService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class ExpressionServiceImpl implements ExpressionService{
    
    @Autowired
    ExpressionRepository expressionRepository;
    
    @Override
    public Expression save(Expression expression) {
        return expressionRepository.saveAndFlush(expression);
    }

    @Override
    public Expression update(Expression expression, Long id) {
        expression.setId(id);
        return expressionRepository.saveAndFlush(expression);
    }
    
    @Override
    public void remove(Long id) {
        expressionRepository.delete(id);
    }

    @Override
    public Expression find(Long id) {
        return expressionRepository.findOne(id);
    }

    @Override
    public Collection<Expression> all() {
        return expressionRepository.findAll();
    }
    
    @Override
    public Collection<Expression> bySituation(Long situationId){
        return expressionRepository.findBySituationId(situationId);
    }
    
    @Override
    public Collection<Expression> forUser(Long situationId, List<String> languages, List<String> desiredLanguages){
        return expressionRepository.findBySituationAndLanguage(
        		situationId, 
        		languages, 
        		desiredLanguages);
    }
    @Override
    public Collection<Expression> forUser(Long situationId, String languages, String desiredLanguages){
        return expressionRepository.findBySituationAndLanguage(
        		situationId,
        		Arrays.asList(languages),
        		Arrays.asList(desiredLanguages));
    }
}
