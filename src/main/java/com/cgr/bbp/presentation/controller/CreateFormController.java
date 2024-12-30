package com.cgr.bbp.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgr.bbp.application.form.usecase.FormCaseUse;
import com.cgr.bbp.infrastructure.persistence.entity.CreateForm.Form;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/form")
public class CreateFormController extends AbstractController {

    @Autowired
    private FormCaseUse formService;

    @PostMapping("/guardar")
    public ResponseEntity<?> saveform(@Valid @RequestBody Form requestForm, BindingResult result) {
        return requestResponse(result, () -> this.formService.sendForm(requestForm), "", HttpStatus.CREATED, true);
    }

    @GetMapping("/getFilterForm")
    public ResponseEntity<?> getFilterForm() {
        return ResponseEntity.ok(this.formService.getFilterForm());
    }

}
