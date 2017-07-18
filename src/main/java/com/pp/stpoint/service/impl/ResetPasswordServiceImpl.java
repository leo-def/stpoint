package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.ResetPasswordToken;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.repository.ResetPasswordTokenRepository;
import com.pp.stpoint.service.ResetPasswordService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    
    @Autowired
    ResetPasswordTokenRepository resetPasswordTokenRepository;
    
    @Override
    public ResetPasswordToken resetPasswordRequest(User user){
    	removeUserTokens(user);
        ResetPasswordToken token = new ResetPasswordToken(UUID.randomUUID().toString(), user);
        return resetPasswordTokenRepository.saveAndFlush(token);
    }
    
    @Override
    public User resetPassword(String token){
        ResetPasswordToken cptoken = resetPasswordTokenRepository.findByToken(token);
        if(cptoken == null){
            return null;
        }
        User user = cptoken.getUser();
        removeUserTokens(user);
        return user;
    }
    
	private void removeUserTokens(User user) {
		resetPasswordTokenRepository.delete(resetPasswordTokenRepository.findByUserId(user.getId()));	
	}
}
/*
 //!!
private boolean isValidToken(ResetPasswordToken token){
    LocalDate date = token.getExpiryDate();
    LocalDate now = LocalDate.now();
    return ( date != null && (date.isAfter(now) || date.isEqual(now)));
}


	@Override
	public void remove(String token) {
		resetPasswordTokenRepository.delete(resetPasswordTokenRepository.findByToken(token));
	}
*/
