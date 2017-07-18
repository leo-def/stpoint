package com.pp.stpoint.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pp.stpoint.dto.ConfirmAccountDTO;
import com.pp.stpoint.dto.GenericDTO;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.service.ConfirmAccountService;
import com.pp.stpoint.service.NotificationService;
import com.pp.stpoint.service.UserService;

@RestController
@RequestMapping(value = "/rest/public/confirmAccount")
public class ConfirmAccountController {

    // !!
    static String CONFIRM_ACCOUNT_TITLE = "Conta confirmada com sucesso";
    static String CONFIRM_ACCOUNT_TEXT = "Agora você pode se credenciar no sistema";
    
    static String CONFIRM_ACCOUNT_ERROR_TITLE = "Token Inválido";
    static String CONFIRM_ACCOUNT_ERROR_TEXT = "O token enviado está inválido";
	
    @Autowired
    UserService userService;
    
    @Autowired
    ConfirmAccountService confirmAccountService;
    
    @Autowired
    NotificationService notificationService;
	 
    @RequestMapping(value = "/", method=RequestMethod.POST)
    public ResponseEntity<GenericDTO> confirmAccount(
            @RequestBody ConfirmAccountDTO dto){
        
        User user = confirmAccountService.confirmAccount(dto.getToken());
        if(user == null)
            return new ResponseEntity<>(new GenericDTO(CONFIRM_ACCOUNT_ERROR_TITLE, CONFIRM_ACCOUNT_ERROR_TEXT), HttpStatus.FORBIDDEN);
        
        user = userService.active(user);
        notificationService.notifyConfirmAccountResponse(user);
        return new ResponseEntity<>(new GenericDTO(CONFIRM_ACCOUNT_TITLE, CONFIRM_ACCOUNT_TEXT), HttpStatus.OK);
    }
}
