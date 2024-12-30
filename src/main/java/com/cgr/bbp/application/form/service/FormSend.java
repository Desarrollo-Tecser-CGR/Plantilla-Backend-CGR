package com.cgr.bbp.application.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgr.bbp.application.form.usecase.FormCaseUse;
import com.cgr.bbp.infrastructure.persistence.entity.CreateForm.Form;
import com.cgr.bbp.infrastructure.persistence.repository.CreateForm.CreateRepository;


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

    @Transactional
    @Override
    public List<Form> getFilterForm() {
        
        List<Form> listForm = createFormRepository.findAll();
        return listForm;
    }

} 


