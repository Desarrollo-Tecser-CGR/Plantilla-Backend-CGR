package com.test.testactivedirectory.presentation.controller.Email;
 
import java.io.File;
import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.testactivedirectory.application.email.dto.EmailRequest;
import com.test.testactivedirectory.application.email.service.*;
import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;
 
import jakarta.servlet.http.HttpServletRequest;
 
 
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
 
    private final EmailService emailService;


    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("/sendId")
    public ResponseEntity<String> send(@RequestBody EmailRequest request) {
        try {
            emailService.sendWithTemplate(request.getRecipients());
            return ResponseEntity.ok("Correos enviados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
