package com.pp.stpoint.controller.rest.authenticated;

import com.pp.stpoint.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value="/rest/authenticated/situation")
public class SituationArticlesController {
    
    @Autowired
    ArticleService articleService;
    
    
    @Autowired
    ModelMapper modelMapper;
    /*
    @RequestMapping(value="/{id}/articles", method = RequestMethod.GET)
    public ResponseEntity<Collection<Article>> all(
            @PathVariable("id") Long id,
            @RequestHeader("STP-Language") String language,
			@RequestHeader("STP-Desired-Language")String desiredLanguage){
    	return new ResponseEntity<>(articleService.forUser(id, language, desiredLanguage),HttpStatus.OK);
    }*/
}

