package com.pp.stpoint.controller.rest;

import com.pp.stpoint.constants.AccountTypes;
import com.pp.stpoint.constants.Authorities;
import com.pp.stpoint.constants.Languages;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping("/rest/public/config")
public class ConfigController {
    
    @RequestMapping(value="/authorities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Authorities[] authorities(){
        return Authorities.values();
    }
    
    @RequestMapping(value="/accountTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountTypes[] accountTypes(){
        return AccountTypes.values();
    }
    
    @RequestMapping(value="/languages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Languages[] languages(){
        return Languages.values();
    }
    
    @RequestMapping(value="/mainLanguages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Languages[] mainLanguages(){
        return Languages.mainLanguages();
    }
    
    @RequestMapping(value="/{language}/languages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Languages[] languages(@PathVariable("language") String language){
        return Languages.languagesFor(language);
    }
}
