package com.ServisKlinickihCentara.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.model.users.User;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

        LocalDate localDate1 = LocalDate.of(2020,7,23);
        LocalDate localDate2 = LocalDate.of(2020,7,23);

        Timestamp start = new Timestamp(System.currentTimeMillis());
        Timestamp end = new Timestamp(start.getTime());
        end.setTime(end.getTime() + TimeUnit.MINUTES.toMillis(5));

        System.out.println(localDate1.equals(localDate2));
        System.out.println(start);
        System.out.println(end);


    }

}
