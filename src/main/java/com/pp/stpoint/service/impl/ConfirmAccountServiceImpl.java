package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.ConfirmAccountToken;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.repository.ConfirmAccountTokenRepository;
import com.pp.stpoint.service.ConfirmAccountService;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class ConfirmAccountServiceImpl implements ConfirmAccountService{

    @Autowired
    ConfirmAccountTokenRepository confirmAccountTokenRepository;

    @Override
    public ConfirmAccountToken createToken(User user) {
    	removeUserTokens(user);
        ConfirmAccountToken token = new ConfirmAccountToken(UUID.randomUUID().toString(), user);
        return confirmAccountTokenRepository.saveAndFlush(token);
    }

    @Override
    public User confirmAccount(String token) {
        ConfirmAccountToken catoken = confirmAccountTokenRepository.findByToken(token);
        if(catoken == null){
            return null;
        }
        User user  =catoken.getUser();
        removeUserTokens(user);
        return user;
    }
    
    public boolean isValidToken(ConfirmAccountToken token){
        LocalDate date = token.getExpiryDate();
        LocalDate now = LocalDate.now();
        return ( date != null && (date.isAfter(now) || date.isEqual(now)));
    }

	@Override
	public void remove(String token) {
		confirmAccountTokenRepository.delete(confirmAccountTokenRepository.findByToken(token));
	}
	
	@Override
	public void removeUserTokens(User user) {
		confirmAccountTokenRepository.delete(confirmAccountTokenRepository.findByUserId(user.getId()));	
	}
    
}
