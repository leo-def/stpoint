package com.pp.stpoint.controller.rest.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.entity.Situation;
import com.pp.stpoint.service.ExpressionService;
import com.pp.stpoint.service.SituationService;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value="/rest/admin/situation")
public class AdminSituationExpressionsController {
    @Autowired
    ExpressionService expressionService;
    
    @Autowired
    SituationService situationService;
    
    
    @Autowired
    ModelMapper modelMapper;
    
    ObjectMapper mapper = new ObjectMapper();
    
    @RequestMapping(value="/{id}/expressions/", method = RequestMethod.GET)
    public ResponseEntity<Collection<Expression>> all(
            @PathVariable("id") Long id){
        
        return new ResponseEntity<>( expressionService.bySituation(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}/expressions/", method = RequestMethod.POST)
    public ResponseEntity<Collection<Expression>> save(
            @PathVariable("id") Long id,
            @RequestBody Expression expression){
        
    	Situation situation = situationService.find(id);
        expression.setSituation(situation);
        expressionService.save(expression);
        return all(id);
    }
    
    @RequestMapping(value="/{id}/expressions/{expressionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Collection<Expression>> remove(
            @PathVariable("id") Long id,
            @PathVariable("expressionId") Long expressionId){
        
        expressionService.remove(expressionId);
        return all(id);
    }
    @RequestMapping(value="/{id}/expressions/{expressionId}", method = RequestMethod.GET)
    public ResponseEntity<Expression> find(
            @PathVariable("id") Long id,
            @PathVariable("expressionId") Long expressionId){
        
        return new ResponseEntity<>( expressionService.find(expressionId), HttpStatus.OK);
    }

    
}
