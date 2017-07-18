package com.pp.stpoint.service;

import com.pp.stpoint.entity.Expression;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ExpressionService {
    Expression save(Expression expression);   
    Expression update(Expression expression, Long id);
    void remove(Long id);
    Expression find(Long id);
    Collection<Expression> all();
    Collection<Expression> bySituation(Long situationId);
    Collection<Expression> forUser(Long situationId, List<String> languages, List<String> desiredLanguages);
    Collection<Expression> forUser(Long situationId, String languages, String desiredLanguages);
}
