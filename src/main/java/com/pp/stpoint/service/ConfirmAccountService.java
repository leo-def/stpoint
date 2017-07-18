package com.pp.stpoint.service;

import com.pp.stpoint.entity.ConfirmAccountToken;
import com.pp.stpoint.entity.User;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ConfirmAccountService {
    ConfirmAccountToken createToken(User user);
    User confirmAccount(String token);
    void remove(String token);
    void removeUserTokens(User user);
}
