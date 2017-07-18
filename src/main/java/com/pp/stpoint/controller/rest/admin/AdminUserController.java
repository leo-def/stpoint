package com.pp.stpoint.controller.rest.admin;

import com.pp.stpoint.dto.ChangePasswordDTO;
import com.pp.stpoint.dto.GenericDTO;
import com.pp.stpoint.dto.EditUserDTO;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.service.FileService;
import com.pp.stpoint.service.UserService;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@RestController
@RequestMapping(value="/rest/admin/user")
public class AdminUserController {
    
    // !!
    static String CHANGE_PASSWORD_TITLE = "Senha alterada com sucesso";
    static String CHANGE_PASSWORD_TEXT = "Agora você pode se authenticar usando outra senha";
    
    static String CHANGE_PASSWORD_ERROR_TITLE = "Não foi possivel alterar sua senha";
    static String CHANGE_PASSWORD_ERROR_TEXT = "Não foi possivel alterar sua senha";
    
    @Autowired
    UserService userService;
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    FileService fileService;
    
    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<Collection<User>> save(
            @RequestBody User user){
        userService.save(user);
        return all();
    }
    
    @RequestMapping(value="/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.POST})
    public ResponseEntity<Collection<User>> edit(
            @PathVariable("id") Long id,
            @RequestBody EditUserDTO dto){
        
        User user = userService.find(id);
        modelMapper.map(dto, user);
        /*if(dto.getAuthority()!= null){
        	user.getGrantedAuthority().setAuthority(dto.getAuthority());
        }*/
        userService.save(user);
        return all();
    }
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Collection<User>> remove(
            @PathVariable("id") Long id){
        
        userService.remove(id);
        return all();
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> find(
            @PathVariable("id") Long id){
        
        return new ResponseEntity<>( userService.find(id), HttpStatus.OK);
    }
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> all(){
       return new ResponseEntity<>( userService.all(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}/changePassword", method = RequestMethod.POST)
    public ResponseEntity<GenericDTO>  changePassword(
            @PathVariable("id") Long id,
            Authentication authentication,
            @RequestBody ChangePasswordDTO changePasswordDTO){
        
        User user = userService.find(id);
        if(userService.matches(changePasswordDTO.getOldPassword(), ((User)authentication.getPrincipal()))){
           userService.setPassword(changePasswordDTO.getNewPassword(), user);
           return new ResponseEntity<>(new GenericDTO(CHANGE_PASSWORD_TITLE, CHANGE_PASSWORD_TEXT), HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericDTO(CHANGE_PASSWORD_ERROR_TITLE, CHANGE_PASSWORD_ERROR_TEXT), HttpStatus.FORBIDDEN);
    }
   
    @RequestMapping(value="/changePassword", method = RequestMethod.POST)
    public ResponseEntity<GenericDTO>  changePassword(
            Authentication authentication,
            @RequestBody ChangePasswordDTO changePasswordDTO){
        
        User user = ((User)authentication.getPrincipal());
        return changePassword(user.getId(), authentication, changePasswordDTO);
     }
    
    
    @RequestMapping(value="/{id}/upload", method={RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Collection<User>> setProfileImage(
            @PathVariable("id") Long id,
            MultipartFile file){
        User user = userService.find(id);
        user.setProfileImageURL(
                fileService.write(User.PROFILE_IMAGE_FOLDER, file)
            );
        userService.save(user);
        return all();
    }
    
    
    
}
