package com.test.testactivedirectory.application.form.usecase;

import com.test.testactivedirectory.infrastructure.persistence.entity.CreateForm.Form;

public interface FormCaseUse {

    public abstract Boolean sendForm(Form form);

}
