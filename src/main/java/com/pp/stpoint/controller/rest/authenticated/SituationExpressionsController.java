package com.pp.stpoint.controller.rest.authenticated;

import com.pp.stpoint.constants.Languages;
import com.pp.stpoint.dto.ExpressionTranslateDTO;
import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.service.ExpressionService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value="/rest/authenticated/situation")
public class SituationExpressionsController {
    
    @Autowired
    ExpressionService expressionService;
    
    
    @Autowired
    ModelMapper modelMapper;
    
    @RequestMapping(value="/{id}/expressions", method = RequestMethod.GET)
    public ResponseEntity<Collection<Expression>> all(
            @PathVariable("id") Long id,
            @RequestHeader("STP-Language") String language,
			@RequestHeader("STP-Desired-Language")String desiredLanguage){
    	return new ResponseEntity<>(expressionService.forUser(id, language, desiredLanguage),HttpStatus.OK);
    }
}

//!!
/*,
    @RequestMapping(value="/{id}/expressions", method = RequestMethod.GET)
    public ResponseEntity<Collection<ExpressionTranslateDTO>> all(
            @PathVariable("id") Long id,
		  	@RequestHeader("STP-Language") String language,
			@RequestHeader("STP-Desired-Language")String desiredLanguage){

List<String> languages = Languages.toStringList(Languages.relatedLanguages(language));
List<String> desiredLanguages = Languages.toStringList(Languages.relatedLanguages(desiredLanguage));
Collection<ExpressionTranslateDTO> resultList = new ArrayList<>();

Collection<Expression> expressions = expressionService.forUser(id,languages,desiredLanguages);
for(Expression expression : expressions){
ExpressionTranslateDTO dto =  ExpressionTranslateDTO.create(expression, languages, desiredLanguages);
resultList.add(dto);
 return new ResponseEntity<Collection<ExpressionTranslateDTO>>(resultList,HttpStatus.OK);
}
*/