package com.pp.stpoint.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pp.stpoint.dto.ErrorDTO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Controller
public class MainController {
    
	 private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
    @Value("${app.base}")
    String base;

    @RequestMapping(value = "/", method= RequestMethod.GET) //headers = {"Accept: text/html"})
    public String index(Model model){
        model.addAttribute("base", base);//
        return "index";
    }
    
    @RequestMapping(value = "/confirmAccount/{token}", method= RequestMethod.GET)  //headers = {"Accept: text/html"})
    public String confirmAccount(Model model, @PathVariable("token") String token) {    	
        model.addAttribute("base", base);
        model.addAttribute("token", token);
        return "index";
    }

    @RequestMapping(value = "/resetPassword/{token}", method= RequestMethod.GET)  //headers = {"Accept: text/html"})
    public String resetPassword(Model model, @PathVariable("token") String token) {    	
        model.addAttribute("base", base);
        model.addAttribute("token", token);
        return "index";
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(HttpServletRequest req, Exception ex){
    	log.error("Request: " + req.getRequestURL() + " raised " + ex);
    	return new ResponseEntity<>(ErrorDTO.forException(ex),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

/*
    
    // !!
    Tentativas para acessa as páginas do angular diretamente


    @RequestMapping(value = "/*", method= RequestMethod.GET, headers = {"Accept=text/html"})
    public String pages(Model model){
        model.addAttribute("base", base);//
        return "index";
    }
 
    @RequestMapping("/**")
    public String app(Model model){
        System.out.println("!!! APP page");
        model.addAttribute("base", base);//
        return "index";
    }
    */
