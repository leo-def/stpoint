package com.pp.stpoint.service.impl;

import com.pp.stpoint.entity.ConfirmAccountToken;
import com.pp.stpoint.entity.ResetPasswordToken;
import com.pp.stpoint.entity.User;
import com.pp.stpoint.mail.EmailSender;
import com.pp.stpoint.service.NotificationService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    
    // !! arrumar outra forma de atribuir este valor
    final String FROM = "STPoint";    
    
    @Value("${app.host.url}")
    String hostUrl;

     @Autowired
     EmailSender emailSender;
    
    @Override
    public boolean notifyCustomMessage(String subject, String text, User user) {
    	Map<String,Object> ctx = new HashMap<>();
        ctx.put("message", text);
        emailSender.prepareAndSend(FROM, user.getEmail().split(","), subject, ctx, "MSG");
        return true;
    }

    @Override
    public boolean notifyResetPasswordRequest(ResetPasswordToken token, User user) {    
    	Map<String,Object> ctx = new HashMap<>();        
        ctx.put("message", "Clique neste link para redefinir sua senha");
        ctx.put("link", hostUrl + "resetPassword/" + token.getToken());
        emailSender.prepareAndSend(FROM, user.getEmail().split(","), "Notify Reset Password Request", ctx, "LINK");
       return true;
    }

    @Override
    public boolean notifyConfirmAccount(ConfirmAccountToken token, User user) {
    	Map<String,Object> ctx = new HashMap<>();
    	ctx.put("message", "Clique neste link para confirmar seu cadastro");
        ctx.put("link", hostUrl + "confirmAccount/" + token.getToken());
        emailSender.prepareAndSend(FROM, user.getEmail().split(","), "Notify Confirm Account", ctx, "LINK");
        return true;
    }

    @Override
    public boolean notifyResetPassword(User user) {
    	Map<String,Object> ctx = new HashMap<>();
        ctx.put("message", "Senha alterada com sucesso");
        emailSender.prepareAndSend(FROM, user.getEmail().split(","), "Notify Reset Password", ctx, "MSG");
        return true;
    }

    @Override
    public boolean notifyConfirmAccountResponse(User user) {
    	Map<String,Object> ctx = new HashMap<>();
        ctx.put("message", "Conta confirmada com sucesso");
        emailSender.prepareAndSend(FROM, user.getEmail().split(","), "Notify Confirm Account Response", ctx, "MSG");
        return true;
    }
    
    
}
