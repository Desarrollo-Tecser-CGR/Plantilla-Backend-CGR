package com.test.testactivedirectory.application.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.form.usecase.FormCaseUse;
import com.test.testactivedirectory.infrastructure.persistence.entity.CreateForm.Form;
import com.test.testactivedirectory.infrastructure.persistence.repository.CreateForm.CreateRepository;

@Service
public class FormSend implements FormCaseUse {

    @Autowired
    private CreateRepository createFormRepository;

    @Transactional
    @Override
    public Boolean sendForm(Form form) {

        if (createFormRepository.save(form) != null) {
            return true;
        } else {
            return false;
        }
    }

}
