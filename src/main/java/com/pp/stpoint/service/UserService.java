package com.pp.stpoint.service;

import com.pp.stpoint.entity.User;
import java.util.Collection;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface UserService {
    User save(User user);
    User update(User user, Long id);
    void remove(Long id);
    User find(Long id);
    Collection<User> all();
    User byUsername(String username);
    User byEmail(String email);
    User register(User register);
    boolean matches(String password, User user);
    User setPassword(String password, User user);
    User active(User user);
}
