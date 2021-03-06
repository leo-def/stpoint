package com.pp.stpoint.controller.rest.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.entity.Value;
import com.pp.stpoint.service.ExpressionService;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.ValueService;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value="/rest/admin/expression")
public class AdminExpressionValuesController {
    
    @Autowired
    ExpressionService expressionService;
    
    @Autowired
    ValueService valueService;
    
    @Autowired
    FileService fileService;
    
    @RequestMapping(value="/{id}/values/", method = RequestMethod.POST)
    public ResponseEntity<Collection<Value>> save(
            @PathVariable("id") Long id,
            @RequestBody Value value){
        
    	Expression expression = expressionService.find(id);
    	//buscar alternativa
    	value.setExpression(expression);
        valueService.save(value);
        return all(id);
    }
    
    @RequestMapping(value="/{id}/values/{valueId}", method = RequestMethod.DELETE)
    public ResponseEntity<Collection<Value>> remove(
            @PathVariable("id") Long id,
            @PathVariable("valueId") Long valueId){
        
        valueService.remove(valueId);
        return all(id);
    }
    
    @RequestMapping(value="/{id}/values/{valueId}", method = RequestMethod.GET)
    public ResponseEntity<Value> find(
            @PathVariable("id") Long id,
            @PathVariable("valueId") Long valueId){
        
        return new ResponseEntity<>(valueService.find(id) , HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}/values", method = RequestMethod.GET)
    public ResponseEntity<Collection<Value>> all(
            @PathVariable("id") Long id){
        
        return new ResponseEntity<>(expressionService.find(id).getValues() , HttpStatus.OK);
    }
    
     @RequestMapping( value = "/{id}/values/{valueId}/upload",method = RequestMethod.POST)
     public ResponseEntity<Collection<Value>> upload(
             @PathVariable("id") Long id,
             @PathVariable("valueId") Long valueId,
             MultipartFile file){
         
         Value value = valueService.find(valueId);
          String url = fileService.update(
                  value.getAudioURL(),
                  Value.AUDIO_FOLDER,
                  file);
          value.setAudioURL(url);
          valueService.save(value);
          return all(id);
     }
}
