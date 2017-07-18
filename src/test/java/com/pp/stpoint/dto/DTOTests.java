package com.pp.stpoint.dto;

import com.pp.stpoint.ApplicationTests;
import com.pp.stpoint.constants.Authorities;
import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.entity.Value;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.entity.UserGrantedAuthority;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public class DTOTests extends ApplicationTests{
    
    @Autowired
    private ModelMapper mapper;
    
    private RegisterDTO dto;
    private User user;
    
    private Expression expression;
    private ExpressionTranslateDTO expressionTranslateDTO;
    
    @Before
    public void setup(){
           dto =  new RegisterDTO();
           dto.setEmail("dto_email");
           dto.setName("dto_name");
           dto.setPassword("dto_password");
           
           user = new User();
           user.setEmail("user_email");
           user.setName("user_email");
           user.setPassword("pasword_email");
           user.setGrantedAuthority(new UserGrantedAuthority(Authorities.GUEST.authority));
           
           expression = new Expression();
           expression.setValues(Arrays.asList(
                   new Value("LAN1","VAL1"),
                   new Value("LAN2","VAL2"),
                   new Value("LAN4","VAL4"),
                   new Value("LAN5","VAL5")
                ));
           
    }
    
    @After
    public void fisish(){
        dto = null;
        user = null;
        
        expression = null;
        expressionTranslateDTO = null;
    }
    
    @Test
    public void registerDTOToUser(){
        user = mapper.map(dto, User.class);
        Assert.assertEquals("failure - emails must be equals", dto.getEmail(), user.getEmail());
        Assert.assertEquals("failure - names must be equals", dto.getName(), user.getName());
        Assert.assertEquals("failure - passwords must be equals", dto.getPassword(), user.getPassword());
        Assert.assertEquals("failure - user authority must be USER type",
                            user.getGrantedAuthority().getAuthority(), Authorities.USER.authority);
    }
    
    @Test
    public void userToCadasterDTO(){
        dto = mapper.map(user, RegisterDTO.class);
        Assert.assertEquals("failure - emails must be equals", dto.getEmail(), user.getEmail());
        Assert.assertEquals("failure - names must be equals", dto.getName(), user.getName());
        Assert.assertEquals("failure - passwords must be equals", dto.getPassword(), user.getPassword());
        Assert.assertEquals("failure - dto authority must be USER type",
                            dto.getGrantedAuthority(), Authorities.USER.authority);
    }
    
    @Test
    public void translateExpresssion(){
        expressionTranslateDTO = new ExpressionTranslateDTO(expression,
                        Arrays.asList("LAN1", "LAN2","LAN3"),
                        Arrays.asList("LAN4","LAN5","LAN6")
                    );
        
        Assert.assertTrue(expressionTranslateDTO.getValues().size() == 2);
        Assert.assertTrue(expressionTranslateDTO.getValues().contains("VAL1"));
        Assert.assertTrue(expressionTranslateDTO.getValues().contains("VAL2"));
        Assert.assertTrue(expressionTranslateDTO.getTranslates().size() == 2);
        Assert.assertTrue(expressionTranslateDTO.getTranslates().contains("VAL4"));
        Assert.assertTrue(expressionTranslateDTO.getTranslates().contains("VAL5"));
    }
}
