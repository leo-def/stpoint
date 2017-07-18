package com.pp.stpoint.controller.rest.authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.service.ExpressionService;

@RestController
@RequestMapping(value="/rest/authenticated/expression")
public class ExpressionController {
	
    @Autowired
    ExpressionService expressionService;
    
    @RequestMapping(value="/{id}/", method = RequestMethod.GET)
    public ResponseEntity<Expression> get(
            @PathVariable Long id){
        
        return new ResponseEntity<>(expressionService.find(id), HttpStatus.OK);
    }
}
