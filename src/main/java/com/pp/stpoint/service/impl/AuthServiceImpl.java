package com.pp.stpoint.service.impl;

import com.pp.stpoint.repository.UserRepository;
import com.pp.stpoint.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
