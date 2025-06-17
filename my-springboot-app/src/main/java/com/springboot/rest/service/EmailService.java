package com.springboot.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        // Format email with greeting and signature
        String formattedBody = "Dear Sir/Madam,\n\n" + body + "\n\nThanks & Regards,\nAbhinav Gupta";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(formattedBody);
        mailSender.send(message);
    }


}