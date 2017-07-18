
package com.pp.stpoint.controller.rest;

import com.pp.stpoint.dto.RegisterDTO;
import com.pp.stpoint.entity.ConfirmAccountToken;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.service.ConfirmAccountService;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.NotificationService;
import com.pp.stpoint.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value = "/rest/public/user")
public class UserController {
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    FileService fileService;
    
    @Autowired
    UserService userService;
   
    @Autowired
    ConfirmAccountService confirmAccountService;
    
    @Autowired
    NotificationService notificationService;
    
    @RequestMapping(value = "/register",
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterDTO> register(
            @RequestBody RegisterDTO cadastreDTO){
        
        User user = userService.register(modelMapper.map(cadastreDTO, User.class));
        ConfirmAccountToken token = confirmAccountService.createToken(user);
        notificationService.notifyConfirmAccount(token, user);
        
        return new ResponseEntity<>(
                 modelMapper.map(user, RegisterDTO.class)
                , HttpStatus.OK);
    }
    
   
    
}
