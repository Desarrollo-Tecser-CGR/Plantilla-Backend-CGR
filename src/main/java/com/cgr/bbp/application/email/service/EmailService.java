package com.cgr.bbp.application.email.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;
import com.cgr.bbp.infrastructure.persistence.repository.user.IUserRepositoryJpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final IUserRepositoryJpa userRepositoryJpa;
    private String imagePath = "src\\main\\resources\\img\\logo.png";

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine,
            IUserRepositoryJpa userRepositoryJpa) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Async
    // public void sendEmailAsync(String to, String subject, String body) {
    // try {
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setTo(to);
    // message.setSubject(subject);
    // message.setText(body);
    // message.setFrom("desarrollador9tecsersas@gmail.com");

    // // Enviar correo
    // mailSender.send(message);
    // System.out.println("Correo enviado a: " + to);

    // } catch (MailException e) {
    // // Manejo de excepciones específicas de correo
    // System.err.println("Error enviando el correo: " + e.getMessage());
    // e.printStackTrace();
    // } catch (Exception e) {
    // // Manejo de cualquier otro tipo de excepción
    // System.err.println("Error inesperado: " + e.getMessage());
    // e.printStackTrace();
    // }
    // }

    public void sendHtmlEmailAsync(String to, String subject, String htmlContent, String imagePath) {
        try {
            // Crear un mensaje MIME
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Configurar el mensaje
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("desarrollador9tecsersas@gmail.com");
            helper.setText(htmlContent, true);

            // Adjuntar la imagen al correo con un Content-ID (CID)
            FileSystemResource image = new FileSystemResource(imagePath);
            helper.addInline("logo", image); // 'logo' será el Content-ID

            // Enviar el correo
            mailSender.send(mimeMessage);
            System.out.println("Correo HTML enviado a: " + to);

        } catch (Exception e) {
            // Manejo de excepciones genéricas
            System.err.println("Error enviando el correo HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendWithTemplate(List<Long> userIds) {
        List<UserEntity> recipients = userRepositoryJpa.findByIdIn(userIds);

        // Enviar correo a cada uno de los usuarios
        recipients.forEach(to -> {
            Map<String, Object> templateDataSetter = new HashMap<>();

            if (to.getEmail() != null) {
                templateDataSetter.put("subject", "Validacion hv");
                templateDataSetter.put("name", to.getSAMAccountName());
                sendHtml(to.getEmail(), "Validacion hv", templateDataSetter, this.imagePath );
            }

        });
    }

    private void sendHtml(String to, String subject, Map<String, Object> templateData, String imagePath ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Procesar el template con Thymeleaf
            Context context = new Context();
            context.setVariables(templateData);
            String htmlContent = templateEngine.process("emailTemplateC", context);

            // Configurar el correo
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("desarrollador9tecsersas@gmail.com");
            helper.setText(htmlContent, true);

            // Adjuntar la imagen al correo con un Content-ID (CID)
            FileSystemResource image = new FileSystemResource(imagePath);
            helper.addInline("logo", image); // 'logo' será el Content-ID

            // Enviar el correo
            mailSender.send(message);
            System.out.println("Correo enviado a: " + to);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al construir el correo: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al enviar correo a " + to + ": " + e.getMessage(), e);
        }
    }
}
