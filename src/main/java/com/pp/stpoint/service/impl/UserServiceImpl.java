package com.pp.stpoint.service.impl;

import com.pp.stpoint.constants.AccountTypes;
import com.pp.stpoint.constants.Authorities;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.entity.UserGrantedAuthority;
import com.pp.stpoint.repository.UserGrantedAuthorityRepository;
import com.pp.stpoint.repository.UserRepository;
import com.pp.stpoint.service.UserService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class UserServiceImpl  implements UserService , InitializingBean{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Value("${app.superuser.name}")
    private String name;
    
    @Value("${app.superuser.username}")
    private String username;
    
    @Value("${app.superuser.password}")
    private String password;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserGrantedAuthorityRepository userGrantedAuthorityRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    
    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user, Long id) {
        user.setId(id);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<User> all() {
        return userRepository.findAll();
    }
    
    @Override
    public User byUsername(String username){
    	return userRepository.findByEmail(username);
    }
    
    @Override
    public User byEmail(String email){
    	return userRepository.findByEmail(email);
    }
    
    @Override
    public User register(User register) {
       register.setPassword(passwordEncoder.encode(register.getPassword()));
       register.setEnabled(false);
       return userRepository.save(register);
    }

    @Override
    public boolean matches(String password, User user) {
        return passwordEncoder.matches(
                password,
                user.getPassword());
    }

    @Override
    public User setPassword(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
        return save(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
         if(userGrantedAuthorityRepository.countByAuthority(Authorities.ADMIN.toString()) > 0){  
            return;
        }
        log.warn("CREATING ADMIN USER  USERNAME: 'admin@admin.com', PASSWORD: 'adminPass23' ");
        User admin = new User(
                this.name,
                this.username,
                AccountTypes.PREMIUM.toString(),
                new UserGrantedAuthority(
                        Authorities.ADMIN.authority), true);
        admin.setPassword(passwordEncoder.encode(this.password));
        userRepository.save(admin);
    }
    
    public User active(User user){
        user.setEnabled(true);
        return save(user);
    }
    /*
    @PostConstruct
    public void init(){
      
    }
    */
    
}
