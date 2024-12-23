package com.test.testactivedirectory.presentation.controller;
    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.application.form.usecase.FormCaseUse;
import com.test.testactivedirectory.infrastructure.persistence.entity.CreateForm.Form;

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

     @GetMapping("/getFilterForm")
     public ResponseEntity<?> getFilterForm() {
       return ResponseEntity.ok(this.formService.getFilterForm());
    }

    
}
