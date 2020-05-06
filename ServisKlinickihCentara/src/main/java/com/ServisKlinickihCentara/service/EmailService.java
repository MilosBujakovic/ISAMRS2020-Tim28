package com.ServisKlinickihCentara.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.model.users.User;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;


    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String email, String subject, String text) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("milosslaven96@gmail.com");
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);

    }

    public static void main(String[] args) {
        final String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);

        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis() + 24*60*60*1000);
        System.out.println(timestamp1);


    }

}
