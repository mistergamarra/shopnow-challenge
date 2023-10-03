package com.mistergamarra.shopnow.notificationsvc.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyByEmail(String orderCode){
        String sub = String.format("Your order %s has been placed", orderCode);
        String body = String.format("Thanks for purchasing an order whit code %s at ShopNow platform, it is being processed by our team!", orderCode);
        emailService.send(new EmailService.MessageParameters(sub,body));
    }
}
