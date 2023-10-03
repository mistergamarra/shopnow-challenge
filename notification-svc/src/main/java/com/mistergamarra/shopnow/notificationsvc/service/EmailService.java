package com.mistergamarra.shopnow.notificationsvc.service;


import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;

import org.simplejavamail.mailer.MailerBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${com.notification-svc.email.username}")
    String username;

    @Value("${com.notification-svc.email.password}")
    String password;

    public void send(MessageParameters params){
        Email email = EmailBuilder.startingBlank()
                .from("From", "argamdvccnt@gmail.com")
                .to("1 st Receiver", "argamdvccnt@gmail.com")
                .withSubject(params.subject)
                .withPlainText(params.body)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 465, username, password)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer();

        mailer.sendMail(email);

    }

    public static class MessageParameters {
        String subject;
        String body;

        public MessageParameters(String subject, String body) {
                this.body = body;
                this.subject = subject;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

}
