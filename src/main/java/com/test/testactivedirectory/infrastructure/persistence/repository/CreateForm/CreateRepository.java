package com.test.testactivedirectory.infrastructure.persistence.repository.CreateForm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.testactivedirectory.infrastructure.persistence.entity.CreateForm.Form;

public interface CreateRepository extends JpaRepository<Form, Integer> {

}
