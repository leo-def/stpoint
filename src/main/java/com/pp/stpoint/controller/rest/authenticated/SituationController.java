package com.pp.stpoint.controller.rest.authenticated;

import com.pp.stpoint.entity.Situation;
import com.pp.stpoint.service.SituationService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value="/rest/authenticated/situation")
public class SituationController {
    
    @Autowired
    SituationService situationService;
    
    @RequestMapping(value="/{id}/", method = RequestMethod.GET)
    public ResponseEntity<Situation> get(
            @PathVariable Long id){
        
        return new ResponseEntity<>(situationService.find(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/parents/byAccountType/{accountType}/", method = RequestMethod.GET)
    public ResponseEntity<Collection<Situation>> parentsByAccountType(
            @PathVariable("accountType") String accountType){
        
       return new ResponseEntity<>( situationService.parentsByAccountType(accountType), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}/byAccountType/{accountType}/", method = RequestMethod.GET)
    public ResponseEntity<Collection<Situation>> byParentsAndByAccountType(
    		@PathVariable("id") Long id,
            @PathVariable("accountType") String accountType){
        
       return new ResponseEntity<>( situationService.byParentAndByAccountType(id, accountType), HttpStatus.OK);
    }
}

/*
@RequestMapping(value="/byAccountType/{accountType}/", method = RequestMethod.GET)
public ResponseEntity<Collection<Situation>> byAccountType(
        @PathVariable String accountType){
    

   return new ResponseEntity<>( situationService.byAccountType(accountType), HttpStatus.OK);
}

@RequestMapping(value="/parents", method = RequestMethod.GET)
public ResponseEntity<Collection<Situation>> parents(){
	return new ResponseEntity<>(situationService.parents(), HttpStatus.OK);
}

@RequestMapping(value="/{id}/situations", method = RequestMethod.GET)
public ResponseEntity<Collection<Situation>> byParent(@PathVariable("id") Long id){
	return new ResponseEntity<>(situationService.byParent(id), HttpStatus.OK);
}
*/