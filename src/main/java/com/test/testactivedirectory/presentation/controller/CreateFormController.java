package com.test.testactivedirectory.presentation.controller;
    

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.testactivedirectory.application.form.usecase.FormCaseUse;
import com.test.testactivedirectory.application.resume.ResumeService;
import com.test.testactivedirectory.infrastructure.persistence.entity.CreateForm.Form;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/form")
public class CreateFormController extends AbstractController{

    @Autowired
    private FormCaseUse formService;

     @PostMapping("/guardar")
     public ResponseEntity<?> saveform(@Valid @RequestBody Form requestForm, BindingResult result) {
         return this.processRequest(result, () -> ResponseEntity.ok(this.formService.sendForm(requestForm)));
     }
}
