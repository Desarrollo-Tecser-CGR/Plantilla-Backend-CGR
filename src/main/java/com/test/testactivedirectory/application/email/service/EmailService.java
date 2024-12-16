package com.test.testactivedirectory.application.email.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmailAsync(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("desarrollador9tecsersas@gmail.com");

            // Enviar correo
            mailSender.send(message);
            System.out.println("Correo enviado a: " + to);

        } catch (MailException e) {
            // Manejo de excepciones específicas de correo
            System.err.println("Error enviando el correo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Manejo de cualquier otro tipo de excepción
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
