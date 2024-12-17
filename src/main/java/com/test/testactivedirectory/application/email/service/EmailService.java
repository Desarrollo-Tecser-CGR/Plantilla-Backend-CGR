package com.test.testactivedirectory.application.email.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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

    public void sendHtmlEmailAsync(String to, String subject, String htmlBody) {
    try {
        // Crear un mensaje Mime
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // Configurar el mensaje
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("desarrollador9tecsersas@gmail.com");
        helper.setText(htmlBody, true); // El segundo parámetro 'true' indica que el contenido es HTML

        // Enviar el correo
        mailSender.send(mimeMessage);
        System.out.println("Correo HTML enviado a: " + to);

    } catch (MailException e) {
        // Manejo de excepciones específicas de correo
        System.err.println("Error enviando el correo HTML: " + e.getMessage());
        e.printStackTrace();
    } catch (MessagingException e) {
        // Manejo de errores en la creación del MimeMessage
        System.err.println("Error al construir el correo HTML: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        // Manejo de cualquier otro tipo de excepción
        System.err.println("Error inesperado: " + e.getMessage());
        e.printStackTrace();
    }
}
}

