package com.pp.stpoint.config;

import com.pp.stpoint.dto.SessionUserDTO;
import com.pp.stpoint.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
         // <source, destination>
        
        
        PropertyMap<User, SessionUserDTO> userToSessionUserDTO = new PropertyMap<User, SessionUserDTO>() {
        	protected void configure(){
                    map().setAuthority(source.getGrantedAuthority().getAuthority());
        	}
        };
        
        ModelMapper modelMapper =  new ModelMapper();
        
        modelMapper.addMappings(userToSessionUserDTO);
       
        return modelMapper;
    }
   
    
}