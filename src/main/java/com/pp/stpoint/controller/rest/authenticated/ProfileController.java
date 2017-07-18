package com.pp.stpoint.controller.rest.authenticated;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pp.stpoint.dto.ChangePasswordDTO;
import com.pp.stpoint.dto.GenericDTO;
import com.pp.stpoint.dto.ProfileDTO;
import com.pp.stpoint.dto.SessionUserDTO;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.UserService;

@RestController
@RequestMapping(value = "/rest/authenticated/profile")
public class ProfileController {

	@Autowired
	ModelMapper modelMapper;
	    
	@Autowired
	FileService fileService;
	    
	@Autowired
	UserService userService;
	    
    // !!
    static String CHANGE_PASSWORD_TITLE = "Senha alterada com sucesso";
    static String CHANGE_PASSWORD_TEXT = "Agora você pode se authenticar no sistema usando outra senha";
    
    static String CHANGE_PASSWORD_ERROR_TITLE = "Não foi possivel alterar sua senha";
    static String CHANGE_PASSWORD_ERROR_TEXT = "Não foi possivel alterar sua senha";
    
    @RequestMapping(value = "/",  method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<SessionUserDTO> profile(
            @RequestBody ProfileDTO dto,
            Authentication authentication) throws JsonProcessingException{
        
        User user = ((User)authentication.getPrincipal());
        modelMapper.map(dto, user);
        userService.save(user);
        return new ResponseEntity<>(modelMapper.map(user, SessionUserDTO.class), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/changePassword",  method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<GenericDTO> changePassword(
            @RequestBody ChangePasswordDTO dto,
            Authentication authentication){
        
        User user = ((User)authentication.getPrincipal());
        if(userService.matches(dto.getOldPassword(), user)){
           userService.setPassword(dto.getNewPassword(), user);
           return new ResponseEntity<>(new GenericDTO(CHANGE_PASSWORD_TITLE, CHANGE_PASSWORD_TEXT), HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericDTO(CHANGE_PASSWORD_ERROR_TITLE, CHANGE_PASSWORD_ERROR_TEXT), HttpStatus.FORBIDDEN);
    }
    
    
    @RequestMapping( value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<SessionUserDTO> profileUpload(
            Authentication authentication,
            MultipartFile file){

    	User user = ((User)authentication.getPrincipal());
         
    	String url = fileService.update(
                user.getProfileImageURL(),
                User.PROFILE_IMAGE_FOLDER,
                file);
        
         user.setProfileImageURL(url);
         userService.save(user);
         return new ResponseEntity<>(modelMapper.map(user, SessionUserDTO.class),HttpStatus.OK);
    }
}
