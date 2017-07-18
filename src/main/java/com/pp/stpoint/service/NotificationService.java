package com.pp.stpoint.service;

import com.pp.stpoint.entity.ConfirmAccountToken;
import com.pp.stpoint.entity.ResetPasswordToken;
import com.pp.stpoint.entity.User;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface NotificationService {
    boolean notifyCustomMessage(String subject, String text, User user);
    //resetPassword
    boolean notifyResetPasswordRequest(ResetPasswordToken token, User user);
    boolean notifyResetPassword(User user);
    //confirmAccount
    boolean notifyConfirmAccount(ConfirmAccountToken token, User user);
    boolean notifyConfirmAccountResponse(User user);
}
