package com.test.testactivedirectory.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.application.resume.ResumeService;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;

@RestController
@RequestMapping("/api/v1/hojadevida")
public class HojadevidaController {

    @Autowired
    private ResumeService resumeservice;

    @PostMapping("guardar")
    public ResponseEntity<?> saveHojadevida(@RequestBody Identity requesIdentity) {

        return ResponseEntity.ok(resumeservice.registrarHojaDeVida(requesIdentity));
    }

}
