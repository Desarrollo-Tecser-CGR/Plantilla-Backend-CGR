package com.cgr.bbp.application.form.usecase;

import com.cgr.bbp.infrastructure.persistence.entity.CreateForm.Form;

public interface FormCaseUse {

    public abstract Boolean sendForm(Form form);

    public abstract Object getFilterForm();

}
