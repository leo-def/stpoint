package com.pp.stpoint.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pp.stpoint.dto.GenericDTO;
import com.pp.stpoint.dto.ResetPasswordDTO;
import com.pp.stpoint.dto.ResetPasswordRequestDTO;
import com.pp.stpoint.entity.ResetPasswordToken;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.service.NotificationService;
import com.pp.stpoint.service.ResetPasswordService;
import com.pp.stpoint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value="/rest/public/resetPassword")
public class ResetPasswordController {
    // !!
    static String RESET_PASSWORD_TITLE = "Solicitação de mudança de senha enviada com sucesso";
    static String RESET_PASSWORD_TEXT = "Você irá receber um link por e-mail para alterar sua senha";
    
    static String RESET_PASSWORD_ERROR_TITLE = "Não foi possivel solicitar mudança de senha";
    static String RESET_PASSWORD_ERROR_TEXT = "Algum erro ocorreu no servidor e esta operação não foi concluida";
    
    
    static String CONFIRM_RESET_PASSWORD_TITLE = "Senha alterada com sucesso";
    static String CONFIRM_RESET_PASSWORD_TEXT = "Senha alterada com sucesso";
    
    static String CONFIRM_RESET_PASSWORD_ERROR_TITLE = "Não foi possivel alterar sua senha";
    static String CONFIRM_RESET_PASSWORD_ERROR_TEXT = "Não foi possivel alterar sua senha";
    
    @Autowired
    UserService userService;
    
    @Autowired
    NotificationService notificationService;
    
    @Autowired
    ResetPasswordService resetPasswordService;
    
    @RequestMapping(value = "/request",  method ={RequestMethod.POST})
    public ResponseEntity<GenericDTO> resetPasswordRequest(@RequestBody ResetPasswordRequestDTO dto) throws JsonProcessingException {
        User user = userService.byEmail(dto.getEmail());
        if(user == null)
        	return new ResponseEntity<>(new GenericDTO(RESET_PASSWORD_ERROR_TITLE, RESET_PASSWORD_ERROR_TEXT), HttpStatus.INTERNAL_SERVER_ERROR);
        
        ResetPasswordToken token = resetPasswordService.resetPasswordRequest(user);       
        notificationService.notifyResetPasswordRequest(token, user);
        // !!
        return new ResponseEntity<>(new GenericDTO(RESET_PASSWORD_TITLE, RESET_PASSWORD_TEXT), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/",  method ={RequestMethod.POST})
    public ResponseEntity<GenericDTO> resetPassword(
            @RequestBody ResetPasswordDTO dto){
        
        User user = resetPasswordService.resetPassword(dto.getToken());
        if(user == null)
        	return new ResponseEntity<>(new GenericDTO(CONFIRM_RESET_PASSWORD_ERROR_TITLE, CONFIRM_RESET_PASSWORD_ERROR_TEXT), HttpStatus.INTERNAL_SERVER_ERROR);
        
        user = userService.setPassword(dto.getPassword(), user);
        notificationService.notifyResetPassword(user);
        // !!
        return new ResponseEntity<>(new GenericDTO(CONFIRM_RESET_PASSWORD_TITLE, CONFIRM_RESET_PASSWORD_TEXT), HttpStatus.OK);
    }

    
}
