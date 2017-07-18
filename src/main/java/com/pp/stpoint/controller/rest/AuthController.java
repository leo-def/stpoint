package com.pp.stpoint.controller.rest;

import com.pp.stpoint.dto.GenericDTO;
import com.pp.stpoint.dto.SessionUserDTO;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.repository.UserRepository;
import com.pp.stpoint.service.AuthService;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.UserService;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value = "/rest/public/auth")
public class AuthController {
    // !!
    static String LOGOUT_TITLE = "Logout";
    static String LOGOUT_TEXT = "Você agora está desconectado";
    
    
    @Autowired
    AuthService authService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    FileService fileService;
    

    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionUserDTO> login(Authentication authentication){
    	User user= (User)authentication.getPrincipal();
    	
        return new ResponseEntity<>(modelMapper.map(user, SessionUserDTO.class), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/logout",
            method = RequestMethod.POST)
    public ResponseEntity<GenericDTO> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>(new GenericDTO(LOGOUT_TITLE, LOGOUT_TEXT), HttpStatus.OK);
    }
}
